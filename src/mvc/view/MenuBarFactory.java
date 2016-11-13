package mvc.view;

import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 * the menu bar factory class
 */
public class MenuBarFactory {
    private static JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu toolsMenu;
    private JMenu helpMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem newMenuItem;
    private JMenuItem redoMenuItem;
    private JMenuItem undoMenuItem;
    private JMenuItem addPluginMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem setLimitMenuItem;
    private JMenuItem startServerMenuItem;
    private JMenuItem sharedPainterMenuItem;
    private JMenuItem about;

    private MenuBarFactory() {
        menuBar = new JMenuBar();
        initComponent();
        assignShortcuts();
        buildMenuBar();
    }

    public static JMenuBar getMenuBar() {
        if (menuBar == null) {
            new MenuBarFactory();
        }
        return menuBar;
    }

    private void initComponent() {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        toolsMenu = new JMenu("Tools");
        helpMenu = new JMenu("Help");

        menuBar = new JMenuBar();
        newMenuItem = new JMenuItem("New");
        saveMenuItem = new JMenuItem("Save");
        openMenuItem = new JMenuItem("Open");
        exitMenuItem = new JMenuItem("Exit");

        undoMenuItem = new JMenuItem("Undo");
        redoMenuItem = new JMenuItem("Redo");

        addPluginMenuItem = new JMenuItem("Add plug-in");
        setLimitMenuItem = new JMenuItem("Set history limit");
        startServerMenuItem = new JMenuItem("Start a server");
        sharedPainterMenuItem = new JMenuItem("Share your painter");

        about = new JMenuItem("About");

        setIcons();
    }

    private void buildMenuBar() {
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);

        toolsMenu.add(addPluginMenuItem);
        toolsMenu.add(setLimitMenuItem);
        toolsMenu.add(startServerMenuItem);
        toolsMenu.add(sharedPainterMenuItem);

        helpMenu.add(about);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);
    }

    private void setIcons() {
        try {

            Image newIcon = ImageIO.read(getClass().getResource("/resources/menubar/fileMenu/newIcon.png"));
            Image openIcon = ImageIO.read(getClass().getResource("/resources/menubar/fileMenu/openIcon.png"));
            Image saveIcon = ImageIO.read(getClass().getResource("/resources/menubar/fileMenu/saveIcon.png"));
            Image exitIcon = ImageIO.read(getClass().getResource("/resources/menubar/fileMenu/exitIcon.png"));

            Image undoIcon = ImageIO.read(getClass().getResource("/resources/menubar/editMenu/undoIcon.png"));
            Image redoIcon = ImageIO.read(getClass().getResource("/resources/menubar/editMenu/redoIcon.png"));

            Image addPluginIcon = ImageIO
                    .read(getClass().getResource("/resources/menubar/toolsMenu/addPluginIcon.png"));
            Image setHistoryLimitIcon = ImageIO
                    .read(getClass().getResource("/resources/menubar/toolsMenu/setHistoryLimitIcon.png"));
            Image startServerIcon = ImageIO
                    .read(getClass().getResource("/resources/menubar/toolsMenu/startServerIcon.png"));
            Image sharePainterIcon = ImageIO
                    .read(getClass().getResource("/resources/menubar/toolsMenu/sharePainterIcon.png"));

            Image aboutIcon = ImageIO.read(getClass().getResource("/resources/menubar/helpMenu/aboutIcon.png"));

            saveMenuItem.setIcon(new ImageIcon(saveIcon));
            newMenuItem.setIcon(new ImageIcon(newIcon));
            openMenuItem.setIcon(new ImageIcon(openIcon));
            exitMenuItem.setIcon(new ImageIcon(exitIcon));

            undoMenuItem.setIcon(new ImageIcon(undoIcon));
            redoMenuItem.setIcon(new ImageIcon(redoIcon));

            addPluginMenuItem.setIcon(new ImageIcon(addPluginIcon));
            setLimitMenuItem.setIcon(new ImageIcon(setHistoryLimitIcon));
            startServerMenuItem.setIcon(new ImageIcon(startServerIcon));
            sharedPainterMenuItem.setIcon(new ImageIcon(sharePainterIcon));

            about.setIcon(new ImageIcon(aboutIcon));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void assignShortcuts() {
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        redoMenuItem
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));

        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
    }
}