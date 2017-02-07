package fileFilters;


import shapes.Shape;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YS team;
 * This class is used to store all data before sending it to the clients or before saving it.
 */
public class DataOfShapes {
    /**
     * list of shapes 
     */
    private  List<Shape> shapes = new ArrayList<>();
    /**
     * file of the current image
     */
    private File imageDirectory;
    /**
     * 
     * @return the image file
     */
    public File getImage() {
        return imageDirectory;
    }

    public void setImageDirctory(File image) {
        imageDirectory = image;
    }

    public List<Shape> getListOfStates() {
        return shapes;
    }

    public void setListOfshapes(List<Shape> sh) {
        shapes = sh;
    }
}
