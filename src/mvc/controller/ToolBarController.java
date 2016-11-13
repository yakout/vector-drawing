package mvc.controller;

import mvc.model.Model;
import mvc.view.MainGuiView;
import mvc.view.ToolBarFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ahmedyakout on 10/26/16.
 * controller of the shapes tool bar.
 */
public class ToolBarController {
    /**
     * shapes tool bar panel
     */
    private JPanel toolBarPanel;

    public ToolBarController() {
        toolBarPanel = ToolBarFactory.getToolBarPanel();
        addListners();
    }

    /**
     * clear the current selected shapes.
     */
    public static void clearSelectedShapes() {
        if (!PainterPanelController.selectedShapes.isEmpty())
            Model.getModel().getShapes().addAll(PainterPanelController.selectedShapes);
        PainterPanelController.selectedShapes.clear();
        MainGuiView.getMainGuiView().repaint();

    }

    void addListners() {
        Component[] components = toolBarPanel.getComponents();
        for (Component component : components) {
            JButton buttonComponent = (JButton) component;
            switch (buttonComponent.getText()) {
            case "Regular Polygon":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.DrawR_Polygon);
                        try {
                            PainterPanelController.PolygonNumberOfSides = Integer.parseInt(
                                    JOptionPane.showInputDialog("please enter number of " + "sides (by default 5 ) "));
                        } catch (Exception p) {

                        }
                        clearSelectedShapes();
                    }
                });
                break;
            case "Line":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.DrawLine);
                        clearSelectedShapes();
                    }
                });
                break;
            case "Oval":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.DrawOval);
                        clearSelectedShapes();
                    }
                });
                break;
            case "Rectangle":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.DrawRect);
                        clearSelectedShapes();
                    }
                });
                break;

            }
        }
    }

    /**
     * add a new listener to a button for the dynamically loaded shape class.
     * @param button new button for the dynamically loaded shape class
     */
    public static void addNewListner(final JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGuiView.setOperation(MainGuiView.Operation.Other);
                MainGuiView.setOtherOperation(button.getText());
                clearSelectedShapes();
            }
        });
    }

}
