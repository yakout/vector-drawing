package mvc.model;

import mvc.controller.ToolBarController;
import mvc.view.MainGuiView;
import mvc.view.ToolBarFactory;
import shapes.Shape;
import javax.swing.JFileChooser;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created on 10/26/16.
 * @auther YS-Team
 * the model class that handles all the received and sending data.
 */
public class Model {
    private static Model model;
    private Map<String, Class> suppotedShapesClassFiles;
    private List<Shape> shapes;
    /**
     * history object it's get updated by eidt command, and get initialized first time when model is initialized
     * with empty history list.
     */
    private History history;

    private Model() {
        suppotedShapesClassFiles = new HashMap<>();
        history = new History();
        shapes = new ArrayList<>();
    }

    /**
     * @return the history.
     */
    public History getHistory() {
        return history;
    }

    /**
     * @return current state of shapes list.
     */
    public List<Shape> getShapes() {
        return shapes;
    }

    /**
     * @return the current the model object.
     */
    public static Model getModel() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    /**
     * update the shapes.
     * @param shapes
     */
    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    /**
     * get a clone of the shapes list.
     * @param shapes
     * @return
     */
    public List<Shape> cloneShapes(List<Shape> shapes) {
        List<Shape> clonedShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            try {
                clonedShapes.add((Shape) shape.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return clonedShapes;
    }

    /**
     * Load new shape dynamically.
     * @param fileChooser file chooser object.
     */
    public void loadNewShape(JFileChooser fileChooser) {
        MainGuiView mainGuiView = MainGuiView.getMainGuiView();
        int returnValue = fileChooser.showOpenDialog(mainGuiView);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getName();
            fileName = fileName.replace(".class", "");
            System.out.println(fileName);

            String directory = fileChooser.getCurrentDirectory().toString();
            directory = directory.replace("/shapes", "");
            System.out.println(directory);

            File file = new File(directory);
            URL url;
            try {
                url = file.toURI().toURL();
                URL[] urls = new URL[]{url};
                ClassLoader classLoader = new URLClassLoader(urls);
                Class  aClass = classLoader.loadClass("shapes." + fileName);
                addNewShape(fileName, aClass);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
                mainGuiView.showError(e1.toString());
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
                mainGuiView.showError(e1.toString());
            }
        }
    }

    /**
     * this method is use to add a new class to the tool bar
     * @param shapeName
     * @param aClass
     */
    private void addNewShape(String shapeName, Class aClass) {
        ToolBarController.addNewListner(ToolBarFactory.getToolBarPanel().addNewShapeButton(shapeName));
        setSuppotedShapesClassFiles(shapeName, aClass);
    }

    /**
     * @return the supported shapes.
     */
    public Map<String, Class> getSuppotedShapesClassFiles() {
        return suppotedShapesClassFiles;
    }

    /**
     * update the supported with shapes that loaded dynamically.
     * @param key
     * @param value
     */
    public void setSuppotedShapesClassFiles(String key, Class value) {
        suppotedShapesClassFiles.put(key, value);
    }

}

