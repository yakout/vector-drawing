package shapes;

import mvc.controller.PainterPanelController;
import shapes.Point;

import java.awt.*;

public class Rectangle extends Polygon {
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
    
    public Rectangle(){
        
    }

    public Rectangle(Point location, Dimensions dimensions) {
        super();
        color = PainterPanelController.selectedColor;
        this.location = location;
        this.dimensions = dimensions;
    }

    @Override
    public void draw(Graphics g) {
        if (fillColor != null&&!PainterPanelController.getPainterPanel().shouldSelect) {
            g.setColor(fillColor);
            g.fillRect(location.x, location.y, dimensions.width, dimensions.height);
        }
        g.setColor(color);
        g.drawRect(location.x, location.y, dimensions.width < 0 ? 0 : dimensions.width,
                dimensions.height < 0 ? 0 : dimensions.height);
        if (PainterPanelController.getPainterPanel().shouldSelect) {
            g.setColor(new Color(0, 0, 0, 20));
            g.fillRect(location.x, location.y, dimensions.width, dimensions.height);
        }

    }

    public double perimeter() {
        return dimensions.width + dimensions.height + dimensions.width + dimensions.height;
    }

    public double area() {
        return dimensions.height * dimensions.width;
    }

    @Override
    public boolean contain(int x, int y) {
        if (x >= location.x && x <= (location.x + dimensions.width) && y >= location.y
                && y <= (location.y + dimensions.height)) {
            return true;
        }
        return false;
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

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {

        return color;
    }

    public void setFillColor(Color color) {
        fillColor = color;
    }

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
        dimensions.width = (dimensions.width + x2 - x1);
        dimensions.height = (dimensions.height + y2 - y1);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Rectangle rectangle = new Rectangle(this.location, this.dimensions);
        rectangle.setColor(getColor());
        rectangle.setFillColor(getFillColor());
        return rectangle;
    }

}
