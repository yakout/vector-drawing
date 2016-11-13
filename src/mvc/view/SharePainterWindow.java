package mvc.view;

/**
 * Created by ahmedyakout on 11/1/16.
 */
import mvc.controller.MainGuiController;
import mvc.view.MainGuiView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SharePainterWindow extends JFrame {
    private JPanel contentPane;
    private JTextField ipAddressTextField;
    private JTextField portTextField;
    private JLabel ipAddressLabel;
    private JLabel portLabel;
    private JButton connectButton;
    private int port;
    private String address;

    public SharePainterWindow() {
        setResizable(false);
        setTitle("Share Your Painter");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(440, 170);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        contentPane = new JPanel();

        ipAddressTextField = new JTextField();
        portTextField = new JTextField();

        ipAddressLabel = new JLabel("Enter an ip address to connect to a server (e.g 192.168.1.106)");
        portLabel = new JLabel("Enter the port where the server is open (e.g 8017)");

        connectButton = new JButton("Connect");


        contentPane.setBackground(Color.gray);
        setContentPane(contentPane);
        contentPane.setLayout(null);


        final int height = 25;
        final int width = 430;
        final int x , y; x = y = 10;
        ipAddressLabel.setBounds(x, y, width, height);

        ipAddressTextField.setColumns(10);
        ipAddressTextField.setBounds(ipAddressLabel.getX(), ipAddressLabel.getY() + y * 2, 250, 25);

        portLabel.setBounds(ipAddressLabel.getX(), ipAddressTextField.getY() * 2, ipAddressLabel.getWidth(), 25);

        portTextField.setColumns(10);
        portTextField.setBounds(ipAddressTextField.getX(), portLabel.getY() + y * 2, 250, 25);

        connectButton.setBounds(x/2, 120, width, height);

        contentPane.add(ipAddressLabel);
        contentPane.add(ipAddressTextField);
        contentPane.add(portLabel);
        contentPane.add(portTextField);
        contentPane.add(connectButton);

        addListeners();
    }

    private void addListeners() {
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    address = ipAddressTextField.getText();
                    port = Integer.parseInt(portTextField.getText());
                    dispose();
                } catch (Exception e) {
                    MainGuiView.getMainGuiView().showError("please enter valid values");
                }
                MainGuiController.getMainGuiController().startListening(address, port);
            }
        });
    }

}

