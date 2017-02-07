package mvc.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10/26/16.
 * Tool bar factory for shapes buttons.
 */
public class ToolBarFactory extends JPanel {
    private static ToolBarFactory toolBarPanel;
    private List<JButton> buttons;
    private JButton drawLineButton;
    private JButton drawOvalButton;
  //  private JButton drawTRiangleButton;
    private JButton drawRectButton;
    private JButton drawRegularPolygon;
    
   
    private ToolBarFactory() {
        initComponents();
        buildToolBar();
    }

    public static ToolBarFactory getToolBarPanel() {
        if (toolBarPanel == null) {
            toolBarPanel = new ToolBarFactory();
        }
        return toolBarPanel;
    }

    private void initComponents() {
        buttons = new ArrayList<>();
       // drawTRiangleButton = new JButton("Triangle");
        drawLineButton = new JButton("Line");
        drawOvalButton = new JButton("Oval");
        drawRectButton = new JButton("Rectangle");
        drawRegularPolygon=new JButton("Regular Polygon");
        
        try {
            Image ellipseIcon = ImageIO.read(getClass().getResource("/toolbar/ellipseIcon.png"));
            Image rectIcon = ImageIO.read(getClass().getResource("/toolbar/rectIcon.png"));
            Image lineIcon = ImageIO.read(getClass().getResource("/toolbar/lineIcon.png"));
            Image polygonIcon = ImageIO.read(getClass().getResource("/toolbar/polygonIcon.png"));

            drawLineButton.setIcon(new ImageIcon(lineIcon));
            drawOvalButton.setIcon(new ImageIcon(ellipseIcon));
            drawRectButton.setIcon(new ImageIcon(rectIcon));
            drawRegularPolygon.setIcon(new ImageIcon(polygonIcon));

        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

        buttons.add(drawLineButton);
        buttons.add(drawOvalButton);
        buttons.add(drawRectButton);
        buttons.add(drawRegularPolygon);

    }

    private void buildToolBar() {
        for (JButton button : buttons) {
            add(button);
            revalidate();
            repaint();
        }
    }

    public JButton addNewShapeButton(String shapeName) {
        JButton newButton = new JButton(shapeName);
        try {
            Image otherShapeIcon = ImageIO.read(getClass().getResource("/toolbar/otherShapeIcon.png"));
            newButton.setIcon(new ImageIcon(otherShapeIcon));
        } catch (IOException e) {
            MainGuiView.getMainGuiView().showError("failed to set icon to the new shape button");
        }
        buttons.add(newButton);
        buildToolBar();
        return newButton;
    }

}
