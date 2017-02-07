package mvc.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.model.Model;
import mvc.view.MainGuiView;
import mvc.view.OprationBarFactory;
import shapes.Shape;

/**
 * This class is use to control every thing in the operation bar
 * @author said
 */
public class OprationBarController {

    private JPanel oprationBarPanel;
    /**
     * set the initial condition
     */
    public OprationBarController() {
        oprationBarPanel = OprationBarFactory.getOprationBarPanel();
        addListners();
    }
    /**
     * this method is use to add listeners to all object in the menu bar
     */
    void addListners() {
        Component[] components = oprationBarPanel.getComponents();
        for (Component component : components) {
            JButton buttonComponent = (JButton) component;
            switch (buttonComponent.getText()) {

            case "Delete":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.Delete);
                        if (!PainterPanelController.selectedShapes.isEmpty())
                            PainterPanelController.selectedShapes.clear();
                        MainGuiView.getMainGuiView().repaint();
                    }
                });
                break;
            case "Move":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.Move);
                    }
                });
                break;
            case "Resize":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.Resize);
                    }
                });
                break;
            case "Colors":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new ColorChooser();
                        
                    }
                });
                break;

            case "Select":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.Select);
                        if (!PainterPanelController.selectedShapes.isEmpty())
                            Model.getModel().getShapes().addAll(PainterPanelController.selectedShapes);
                        PainterPanelController.selectedShapes.clear();
                        MainGuiView.getMainGuiView().repaint();

                    }
                });
                break;
            case "Fill":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.Fill);
                        if (!PainterPanelController.selectedShapes.isEmpty())
                            for (Shape sh : PainterPanelController.selectedShapes)
                                sh.setFillColor(PainterPanelController.selectedColor);
                        
                        MainGuiView.getMainGuiView().repaint();
                    }
                });
                break;
            }
            MainGuiView.getMainGuiView().repaint();
        }
    }

}
