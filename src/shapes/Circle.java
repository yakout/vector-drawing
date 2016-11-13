package shapes;

/**
 * class circle
 * @author said
 *
 */
public class Circle extends Ellipse {
    /**
     * radius of the circle
     */
    private int radius;

    public Circle() {
    }

    public Circle(Point location, Dimensions dimention) {
        super();
        radius=Math.max(Math.abs(dimention.width),Math.abs(dimention.height));
        location.x-=radius/2;
        location.y-=radius/2;
        setLocation(location);
        setDimensions(new Dimensions(radius, radius));
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
