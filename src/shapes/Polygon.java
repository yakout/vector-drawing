package shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import mvc.controller.PainterPanelController;

public abstract class Polygon implements Shape {
    /**
     * list of polygon points
     */
    private List<Point> points = new ArrayList<>();
    /**
     * shape color default is black
     */
    private Color color = Color.black;

    /**
     * shape area color default null
     */

    private Color fillColor = null;

    /**
     * constructor
     * 
     * @param points
     *            of the shape
     */
    public Polygon(List<Point> points) {
        this.color = PainterPanelController.selectedColor;
        this.points = points;
    }

    public Polygon() {

    }

    @Override
    public void draw(Graphics g) {
        /**
         * polygon points number
         */
        int numberOfPoints = points.size();
        /**
         * list of x of shape points
         */
        int[] xPoints = new int[numberOfPoints];
        /**
         * list of y of shape points
         */
        int[] yPoints = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            xPoints[i] = points.get(i).x;
            yPoints[i] = points.get(i).y;
        }
        g.drawPolygon(xPoints, yPoints, points.size());
    }

    /**
     * @return the points of the polygon.
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * set the points of the polygon
     * 
     * @param points
     */

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public boolean contain(int x, int y) {

        return false;
    }

    @Override
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
        return super.clone();
    }
}