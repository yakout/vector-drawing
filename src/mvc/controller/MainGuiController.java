package mvc.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import fileFilters.DataOfShapes;
import mvc.model.Model;
import mvc.view.MainGuiView;
import server.Client;

/**
 * the MainGuiController is controller part of the MainGuiView view it's get
 * called by @see .LaunchGui it's responsible to call MainGuiView and other
 * controllers
 */
public class MainGuiController {
    /**
     * singelton object of the MainGuiView
     */
    private MainGuiView mainGuiView;
    private Thread listenThread, runThread;
    private Client client;
    private static MainGuiController mainGuiController;

    public MainGuiController() {
        mainGuiController = this;

        runThread = new Thread() {
            @Override
            public void run() {
                // load model
                Model.getModel();

                // load all views
                mainGuiView = MainGuiView.getMainGuiView();

                // show the up after it has finished setting up the gui
                // components
                mainGuiView.setVisible(true);

                // load all controllers
                new ToolBarController();
                new OprationBarController(); // saeed : changed
                new MenuBarController();
                // controllers methods
                addListners();
            }
        };
        runThread.start();

    }

    /**
     * add all the listeners to the MainGuiView view
     */
    void addListners() {
        mainGuiView.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
    }

    /**
     * get called when user try to closes the window
     * 
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        mainGuiView.showConfirm("sure?");
    }

    /**
     * Start sharing the application between client and the server   
     * @param clientAddress
     * @param clientPort
     */
    public void startListening(String clientAddress, int clientPort) {
        client = new Client(clientAddress, clientPort);

        boolean connected = client.connect();
        if (!connected) {
            mainGuiView.showError("Connection failed!");
            return;
        }

        mainGuiView
                .showMessage("Connection successful" + "\nConnecting to " + clientAddress + ":" + clientPort + "...");

        listenThread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        String message = client.receive();
                        if (message.startsWith("Request")) {
                            continue;
                        }
                        toShapes(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                        mainGuiView.showError(e.toString());
                    }
                }
            }
        };
        listenThread.start();
    }

    public void sendShapes() {
        XStream json = new XStream(new JettisonMappedXmlDriver());
        DataOfShapes data = new DataOfShapes();
        data.setListOfshapes(Model.getModel().getShapes());
        json.setMode(XStream.NO_REFERENCES);
        json.alias("data", DataOfShapes.class);
        client.send(json.toXML(data).getBytes());
    }

    public void toShapes(String jsonStr) {
        DataOfShapes data;
        XStream json = new XStream(new JettisonMappedXmlDriver());
        json.alias("data", DataOfShapes.class);
        data = (DataOfShapes) json.fromXML(jsonStr);
        Model.getModel().setShapes(data.getListOfStates());
        PainterPanelController.getPainterPanel().repaint();
    }

    public static MainGuiController getMainGuiController() {
        return mainGuiController;
    }

    public Client getClient() {
        return client;
    }
}
