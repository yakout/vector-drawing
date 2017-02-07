package shapes;

/**
 * class 2Dsqure
 * 
 * @author said
 *
 */

public class Square extends RegulerPolygon {
    private static final int numberOfSides = 4;

    public Square (){
    }

    public Square(Point location, Dimensions dimensions) {
        super(location, dimensions, numberOfSides);
    }
}
