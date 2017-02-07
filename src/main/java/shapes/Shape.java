package shapes;

import java.awt.Graphics;
import java.awt.Color;

/**
 * @author YSteam
 */
public interface Shape {
    /**
     * draw the shape
     * 
     * @param g
     *            graphic object to draw with
     */
    public void draw(Graphics g);

    /**
     * set the location of the shape
     * 
     * @param location
     */

    public void setLocation(Point location);

    /**
     * 
     * @return location of the shape
     */

    public Point getLocation();

    /**
     * 
     * @return the dimantion of the shape
     */

    public Dimensions getDimensions();

    /**
     * set dimensions of the shape (width,height)
     * 
     * @param dimensions
     */

    public void setDimensions(Dimensions dimensions);

    /**
     * 
     * @param x
     * @param y
     * @return true if the shape contain this point false otherwise
     */

    public boolean contain(int x, int y);

    /**
     * set the shape color
     * 
     * @param color
     */

    public void setColor(Color color);

    /**
     * 
     * @return shape color
     */

    public Color getColor();

    /**
     * set the shape area color
     * 
     * @param color
     */

    public void setFillColor(Color color);

    /**
     * 
     * @return the shape area color
     */

    public Color getFillColor();

    /**
     * 
     * @return a copy from the shape
     * @throws CloneNotSupportedException
     */

    public Object clone() throws CloneNotSupportedException;

    /**
     * move the shape from old location (x2,y1) to new (x2,y2)
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */

    public void move(int x1, int y1, int x2, int y2);

    /**
     * reset the size of the shape by the change in coordinates
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */

    public void resize(int x1, int y1, int x2, int y2);
}
