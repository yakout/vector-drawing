package mvc.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import fileFilters.DataOfShapes;
import mvc.model.Model;

import fileFilters.pngSaveFilter;
import fileFilters.JasonSaveFileFilter;
import fileFilters.xmlSaveFilter;


/**
 * open file chooser to load file from pc (json, xml, image).
 */
@SuppressWarnings("serial")
public class OpenFile extends JFrame {
    public static BufferedImage image;
    public static File imageDirectory=null;    
    
    public OpenFile() throws ClassNotFoundException {
        JFileChooser openFile = new JFileChooser();
        openFile.addChoosableFileFilter(new pngSaveFilter());
        openFile.addChoosableFileFilter(new JasonSaveFileFilter());
        openFile.addChoosableFileFilter(new xmlSaveFilter());
        int result = openFile.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (openFile.getSelectedFile().getName().endsWith(".png")) {
                try {
                    imageDirectory=openFile.getSelectedFile();                    
                    image = ImageIO.read(openFile.getSelectedFile());
                    MainGuiView.getMainGuiView().repaint();
                } catch (IOException ex) {
                }
            } else if (openFile.getSelectedFile().getName().endsWith(".json")) {

                try {
                    DataOfShapes data;
                    XStream json = new XStream(new JettisonMappedXmlDriver());
                    json.alias("data", DataOfShapes.class);
                    data = (DataOfShapes) json.fromXML(openFile.getSelectedFile());
                    Model.getModel().setShapes(data.getListOfStates());
                    imageDirectory=data.getImage();
                    if(imageDirectory!=null)
                        image = ImageIO.read(imageDirectory);
                    
                     MainGuiView.getMainGuiView().repaint();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            } else if (openFile.getSelectedFile().getName().endsWith(".xml")) {
                try {
                    DataOfShapes data ;
                    XStream xml = new XStream(new StaxDriver());
                    xml.alias("data", DataOfShapes.class);
                    data = (DataOfShapes) xml.fromXML(openFile.getSelectedFile());
                    Model.getModel().setShapes(data.getListOfStates());
                    imageDirectory=data.getImage();
                    if(imageDirectory!=null)
                        image = ImageIO.read(imageDirectory);
                    MainGuiView.getMainGuiView().repaint();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
