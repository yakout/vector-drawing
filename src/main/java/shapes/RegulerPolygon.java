package shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import mvc.controller.PainterPanelController;

public class RegulerPolygon extends Polygon {
    private List<Point> points = new ArrayList<>();
    /**
     * location of the shape
     */
    private Point location;
    /**
     * Dimensions of the shape
     */
    private Dimensions dimensions;
    /**
     * shape color default is black
     */
    private Color color = Color.black;

    /**
     * shape area color default null
     */

    private Color fillColor = null;
    /**
     * number of shape sides
     */
    private int NunuOfSides;
    
    public RegulerPolygon(){
        
    }

    /**
     * constructor
     * 
     * @param location
     * @param dimensions
     * @param sides
     */
    public RegulerPolygon(Point location, Dimensions dimensions, int sides) {

        this.dimensions = dimensions;
        NunuOfSides = sides;
        this.location = location;
        this.color = PainterPanelController.selectedColor;
        intialize();

    }

    /**
     * Initialize or update the state of the shape
     */
    private void intialize() {
        points.clear();
        double theta = Math.toRadians(360 / NunuOfSides);
        points.add(0, new Point(location.x + dimensions.width, location.y + dimensions.height));

        for (int i = 0; i < NunuOfSides - 1; i++) {
            int x = (int) (location.x + (points.get(i).x - location.x) * Math.cos(theta)
                    - (points.get(i).y - location.y) * (Math.sin(theta)));
            int y = (int) (location.y + (points.get(i).x - location.x) * Math.sin(theta)
                    + (points.get(i).y - location.y) * (Math.cos(theta)));
            points.add(new Point(x, y));
        }
    }

    /**
     * 
     * @param points
     */
    public RegulerPolygon(List<Point> points) {
        this.color = PainterPanelController.selectedColor;
        this.points = points;
        NunuOfSides = points.size();
    }

    @Override
    public void draw(Graphics g) {
        int numberOfPoints = points.size();
        int[] xPoints = new int[numberOfPoints];
        int[] yPoints = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            xPoints[i] = points.get(i).x;
            yPoints[i] = points.get(i).y;
        }

        if (fillColor != null && !PainterPanelController.getPainterPanel().shouldSelect) {
            g.setColor(fillColor);
            g.fillPolygon(xPoints, yPoints, numberOfPoints);
        }
        g.setColor(color);
        g.drawPolygon(xPoints, yPoints, numberOfPoints);
        if (PainterPanelController.getPainterPanel().shouldSelect) {
            g.setColor(new Color(0, 0, 0, 20));
            g.fillPolygon(xPoints, yPoints, numberOfPoints);

        }
    }

    public boolean contain(int x, int y) {
        int numberOfPoints = points.size();
        int[] xPoints = new int[numberOfPoints];
        int[] yPoints = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            xPoints[i] = points.get(i).x;
            yPoints[i] = points.get(i).y;
        }
        java.awt.Polygon p = new java.awt.Polygon(xPoints, yPoints, numberOfPoints);
        return p.contains(x, y);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setFillColor(Color color) {
        fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        RegulerPolygon polyg = new RegulerPolygon(points);
        polyg.setColor(getColor());
        polyg.setFillColor(getFillColor());
        return polyg;
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void move(int x1, int y1, int x2, int y2) {
        location.x += x2 - x1;
        location.y += y2 - y1;
        intialize();

    }

    @Override
    public void resize(int x1, int y1, int x2, int y2) {
        dimensions.width += x2 - x1;
        dimensions.height += y2 - y1;
        intialize();
    }
}