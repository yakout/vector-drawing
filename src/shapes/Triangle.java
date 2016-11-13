package shapes;

public class Triangle extends RegulerPolygon {
    /**
     * number of shape sides
     */
    private final static int nuOfSides = 3;

    /**
     * 
     * @param location
     * @param dimensions
     */
    public Triangle() {
    }

    public Triangle(Point location, Dimensions dimensions) {
        super(location, dimensions, nuOfSides);
    }

}
