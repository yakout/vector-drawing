package mvc.view;

import server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by khlailmohammedyakout on 11/3/16.
 * window opened when user need to start a server
 */
public class StartServerWindow extends JFrame {
    private JPanel contentPane;
    private JTextField portTextField;
    private JLabel portLabel;
    private JButton startServer;
    private int port;

    public StartServerWindow() {
        setResizable(false);
        setTitle("Share Your Painter");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(440, 120);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        contentPane = new JPanel();

        portTextField = new JTextField();

        portLabel = new JLabel("Enter a port number to start a server on your localhost (e.g 8017)");

        startServer = new JButton("Start");


        contentPane.setBackground(Color.gray);
        setContentPane(contentPane);
        contentPane.setLayout(null);


        final int height = 25;
        final int width = 430;
        final int x , y; x = y = 10;
        portLabel.setBounds(x, y, width, height);

        portTextField.setColumns(10);
        portTextField.setBounds(portLabel.getX(), portLabel.getY() + y * 3, 250, 25);

        startServer.setBounds(x/2, 70, width, height);

        contentPane.add(portLabel);
        contentPane.add(portTextField);
        contentPane.add(startServer);

        addListeners();
    }

    private void addListeners() {
        startServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    port = Integer.parseInt(portTextField.getText());
                    dispose();
                } catch (Exception e) {
                    MainGuiView.getMainGuiView().showError("Please enter valid values");
                }
                try {
                    new Server(port);
                } catch (Exception e) {
                    MainGuiView.getMainGuiView().showError("Failed opening a server, please double check port availability");
                }

            }
        });
    }
}
