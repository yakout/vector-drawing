package mvc.view;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Factory class for the operation buttons.
 */
public class OprationBarFactory extends JPanel {
    /**
     * singelton object for OprationBarFactory
     */
    private static OprationBarFactory operationBarPanel;
    /**
     * list of operation button.
     */
    private List<JButton> operationButtons;
    /**
     * resize the shape operation button.
     */
    private JButton resizeButton;
    /**
     * delete the shape operation button.
     */
    private JButton deleteButton;
    /**
     * move the shape button operation.
     */
    private JButton moveButton;
    /**
     * color chooser operation button.
     */
    private JButton colorButton;
    /**
     * select the shape operation button.
     */
    private JButton selectButton;
    /**
     * fill the shape operation button.
     */
    private JButton fillShapeButton;

    private OprationBarFactory() {
        initComponents();
        buildToolBar();
    }

    /**
     * @return singelton object of OprationBarFactory
     */
    public static OprationBarFactory getOprationBarPanel() {
        if (operationBarPanel == null) {
            operationBarPanel = new OprationBarFactory();
        }
        return operationBarPanel;
    }

    /**
     * intialize components.
     */
    private void initComponents() {
        operationButtons = new ArrayList<>();
        resizeButton = new JButton("Resize");
        deleteButton = new JButton("Delete");
        moveButton = new JButton("Move");
        colorButton = new JButton("Colors");
        selectButton = new JButton("Select");
        fillShapeButton = new JButton("Fill");

        setButtonsIcon();

        operationButtons.add(moveButton);
        operationButtons.add(resizeButton);
        operationButtons.add(deleteButton);
        operationButtons.add(colorButton);
        operationButtons.add(selectButton);
        operationButtons.add(fillShapeButton);
    }

    /**
     * set the buttons icons.
     */
    private void setButtonsIcon() {
        try {
            Image resizeIcon = ImageIO.read(getClass().getResource("/resources/toolbar/resizeIcon.png"));
            Image moveIcon = ImageIO.read(getClass().getResource("/resources/toolbar/moveIcon.png"));
            Image deleteIcon = ImageIO.read(getClass().getResource("/resources/toolbar/deleteIcon.png"));
            Image colorChooserIcon = ImageIO.read(getClass().getResource("/resources/toolbar/colorChooserIcon.png"));
            Image selectIcon = ImageIO.read(getClass().getResource("/resources/toolbar/selectIcon.png"));
            Image fillIcon = ImageIO.read(getClass().getResource("/resources/toolbar/fillIcon.png"));

            resizeButton.setIcon(new ImageIcon(resizeIcon));
            deleteButton.setIcon(new ImageIcon(deleteIcon));
            moveButton.setIcon(new ImageIcon(moveIcon));
            colorButton.setIcon(new ImageIcon(colorChooserIcon));
            selectButton.setIcon(new ImageIcon(selectIcon));
            fillShapeButton.setIcon(new ImageIcon(fillIcon));

        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * building the operation tool bar.
     */
    private void buildToolBar() {
        for (JButton button : operationButtons) {
            add(button);
            revalidate();
            repaint();
        }
    }
}