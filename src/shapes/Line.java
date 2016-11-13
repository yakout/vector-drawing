package shapes;

import java.awt.Color;
import java.awt.Graphics;
import mvc.controller.PainterPanelController;

/**
 * 
 * @author YS team class 2Dline
 */

public class Line implements Shape, Cloneable {
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
    public Line (){
        
    }

    public Line(int x1, int y1, int x2, int y2) {
        color = PainterPanelController.selectedColor;

        location = new Point(x1, y1);
        dimensions = new Dimensions(x2 - x1, y2 - y1);

    }

    public Line(Point location, Dimensions dimensions) {
        color = PainterPanelController.selectedColor;
        this.location = location;
        this.dimensions = dimensions;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawLine(location.x, location.y, location.x + dimensions.width, location.y + dimensions.height);
        if (PainterPanelController.getPainterPanel().shouldSelect) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(location.x, location.y, location.x + dimensions.width, location.y + dimensions.height);

        }

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
    public boolean contain(int x, int y) {
        // TODO Auto-generated method stub
        if (x <= Math.max(location.x, location.x + dimensions.width)
                && x >= Math.min(location.x, location.x + dimensions.width))
            try {
                // System.out.println("gg");

                return (((x - location.x) / (y - location.y)) == (location.x + dimensions.width - location.x)
                        / (location.y + dimensions.height - location.y));
            } catch (Exception exp) {
                System.out.println("kkll");
                return (Math.abs(Math.ceil((x - location.x) * (dimensions.height / 10))
                        - Math.ceil(y - location.y) * (dimensions.width / 10)) <= 400);

            }

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
        this.color = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void move(int x1, int y1, int x2, int y2) {
        location.x += x2 - x1;
        location.y += y2 - y1;
    }

    @Override
    public void resize(int x1, int y1, int x2, int y2) {
        System.out.println(x1 + "" + x2 + "" + y1 + "" + y2);
        dimensions.width = (dimensions.width + x2 - x1);
        dimensions.height = (dimensions.height + y2 - y1);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Line clonedShape = new Line(this.location, this.dimensions);
        clonedShape.setColor(clonedShape.getColor());
        clonedShape.setFillColor(clonedShape.getColor());
        return clonedShape;
    }

}
