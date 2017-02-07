package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import mvc.controller.PainterPanelController;

/**
 * Class of 2DEllipse
 * 
 * @author Ys team
 *
 */

public class Ellipse implements Shape {
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
     * constructor
     * 
     * @param points
     *            of the shape
     */

    public Ellipse (){
        
    }
    public Ellipse(Point location, Dimensions dimensions) {
        color = PainterPanelController.selectedColor;
        this.location = location;
        this.dimensions = dimensions;
    }

    @Override
    public void draw(Graphics g) {

        if (getFillColor() != null&&! PainterPanelController.getPainterPanel().shouldSelect) {
            g.setColor(getFillColor());
            g.fillOval(location.x, location.y, dimensions.width, dimensions.height);
        }
        g.setColor(getColor());
        g.drawOval(location.x, location.y, dimensions.width < 0 ? 0 : dimensions.width,
                dimensions.height < 0 ? 0 : dimensions.height);
        if (PainterPanelController.getPainterPanel().shouldSelect) {
            g.setColor(new Color(0, 0, 0, 20));
            g.fillOval(location.x, location.y, dimensions.width, dimensions.height);
        }
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
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    public boolean contain(int x, int y) {
        final Ellipse2D el = new Ellipse2D.Float(location.x, location.y, dimensions.width < 0 ? 0 : dimensions.width,
                dimensions.height < 0 ? 0 : dimensions.height);
        return el.contains(x, y);
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
        Ellipse copyEllipse = new Ellipse(this.getLocation(), this.getDimensions());
        copyEllipse.setFillColor(getFillColor());
        copyEllipse.setColor(getColor());
        return copyEllipse;
    }
}
