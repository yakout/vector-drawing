package server;

/**
 * Created by ahmedyakout on 11/1/16.
 */
import mvc.view.MainGuiView;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * The server class, an instance will be created if the user started a new server.
 * @author ahmedyakout
 */
public class Server implements Runnable {
    /**
     * List of clients connected in the server.
     */
    private List<ServerClient> clients = new CopyOnWriteArrayList<>();

    /**
     * UDP socket.
     */
    private DatagramSocket socket;
    /**
     * Port the server running on.
     */
    private int port;
    /**
     * Threads run, send, and receive that will run the methods run, send, and receive respectively.
     */
    private Thread runThread, sendThread, receiveThread;


    public Server(int serverPort) {
        port = serverPort;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            return;
        }
        runThread = new Thread(this, "Server");
        runThread.start();
    }

    /**
     * Running the server.
     */
    public void run() {
        MainGuiView.getMainGuiView().showMessage("Server started on port " + port);
        receive();
    }

    /**
     * listening on the port to receive any data sent by connected clients.
     */
    private void receive() {
        receiveThread = new Thread("Receive") {
            public void run() {
                while (true) {
                    byte[] data = new byte[6400];
                    DatagramPacket packet = new DatagramPacket(data, data.length);
                    try {
                        socket.receive(packet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String packetString = new String(packet.getData());
                    if (packetString.startsWith("Request")) {
                        clients.add(new ServerClient(packet.getAddress(), packet.getPort()));
                        continue;
                    }
                    sendToClients(packetString);
                }

            }
        };
        receiveThread.start();
    }

    /**
     * Send any received to connected clients.
     * @param packetString the received messages.
     */
    private void sendToClients(String packetString) {
        for (int i = 0; i < clients.size(); i++) {
            ServerClient client = clients.get(i);
            send(packetString.getBytes(), client.getAddress(), client.getPort());
        }
    }

    /**
     * Send a received data to specific client.
     * @param data received data to be send to the a client.
     * @param addressSendTo - the destination address.
     * @param portSendTo - the destination port number.
     */
    private void send(final byte[] data, final InetAddress addressSendTo, final int portSendTo) {
        sendThread = new Thread() {
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, addressSendTo, portSendTo);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        sendThread.start();
    }
}
