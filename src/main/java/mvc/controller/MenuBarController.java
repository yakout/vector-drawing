package mvc.controller;

import mvc.model.History;
import mvc.model.Model;
import mvc.model.RedoCommand;
import mvc.model.UndoCommand;
import mvc.view.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This class is use to control every thing in the menu bar
 * Created by khlailmohammedyakout on 10/26/16.
 *  
 */
public class MenuBarController {
    /**
     * the menu bar
     */
    private JMenuBar menuBar;
    /**
     * the file chooser object
     */
    private JFileChooser fileChooser;
    /**
     * the Main application GUI object 
     * will be use to apply changes
     */
    private MainGuiView mainGuiView;
    /**
     * the tool bar object
     */


    public MenuBarController() {
        menuBar = MenuBarFactory.getMenuBar();
        mainGuiView = MainGuiView.getMainGuiView();
        addListners();
    }

    private void addListners() {
        Component[] components = menuBar.getComponents();
        for (Component component : components) {
            Component[] menuComponents = ((JMenu) component).getMenuComponents();
            for (Component menuComponent : menuComponents) {
                if(menuComponent instanceof JSeparator) continue;
                JMenuItem menuItem = (JMenuItem) menuComponent;
                switch (menuItem.getText()) {
                
                    case "Open":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    new OpenFile();
                                } catch (ClassNotFoundException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        });
                        break;

                    case "Add plug-in":
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                fileChooser = new JFileChooser();
                                FileFilter classFilesFilter = new FileFilter() {
                                    @Override
                                    public boolean accept(File f) {
                                        if (f.isDirectory()) {
                                            return true;
                                        }
                                        String s = f.getName();
                                        return s.endsWith(".class") || s.endsWith(".CLASS");
                                    }

                                    @Override
                                    public String getDescription() {
                                        return ".class, .CLass";
                                    }
                                };
                                fileChooser.addChoosableFileFilter(classFilesFilter);
                                Model.getModel().loadNewShape(fileChooser);
                            }
                        });
                        break;
                    case "Save":
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new SaveFile();
                            }
                        });
                        break;
                    case "Exit":
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainGuiView.showConfirm("Sure");
                            }
                        });
                        break;
                    case "Redo":
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                PainterPanelController.getPainterPanel().doneSelecting();
                                new RedoCommand().execute();
                            }
                        });
                        break;

                    case "Undo":
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                PainterPanelController.getPainterPanel().doneSelecting();
                                new UndoCommand().execute();
                            }
                        });
                        break;

                    case "Set history limit":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                History history = Model.getModel().getHistory();
                                String input = JOptionPane.showInputDialog("please select limit" +
                                        " (current limit is " + history.getLimit() + ")");
                                if(input != null && !input.equals("")) {
                                    history.setLimit(Integer.parseInt(input));
                                }
                            }
                        });
                        break;

                    case "Share your painter":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                new SharePainterWindow().setVisible(true);
                            }
                        });
                        break;

                    case "Start a server":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                new StartServerWindow().setVisible(true);
                            }
                        });
                        break;

                    case "About":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null, "version 1.0\n\nDeveloped with love " +
                                        "by:\nAhmed Yakout\nSaed Hamdy");
                            }
                        });
                        break;
                    case "New":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Model.getModel().getShapes().clear();
                                PainterPanelController.getPainterPanel().repaint();
                            }
                        });
                        break;
                }
            }
        }
    }

}
