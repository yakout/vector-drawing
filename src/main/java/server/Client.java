package server;

/**
 * Created by ahmedyakout on 11/1/16.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * The client class, every user will connect to the server will create an instance of this class.
 * @author ahmedyakout
 */
public class Client {
    /**
     * The address of the client.
     */
    private String address;
    /**
     * The port of the client.
     */
    private int port;
    /**
     * Client ip.
     */
    private InetAddress ip;

    /**
     * UDP socket.
     */
    private DatagramSocket socket;

    /**
     * Send and close threads that will run the close and send methods.
     */
    private Thread sendThread, closeThread;

    public Client(String clientAddress, int clientPort) {
        address = clientAddress;
        port = clientPort;
    }

    /**
     * Make a connection to the server.
     * @return
     */
    public boolean connect() {
        try {
            System.out.println("connecting..");
            socket = new DatagramSocket();
            ip = InetAddress.getByName(address);
            send("Request".getBytes());
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Receive data from server.
     * @return
     */
    public String receive() {
        byte[] data = new byte[6400];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = new String(packet.getData());
        return message;
    }

    /**
     * Send the data to the server in bytes.
     * @param data data that will be sent in bytes.
     */
    public void send(final byte[] data) {
        sendThread = new Thread("Send") {
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        sendThread.start();
    }

    /**
     * Close the client connect to the server once he closed the application.
     */
    public void close() {
        closeThread = new Thread() {
            public void run() {
                synchronized (socket) {
                    socket.close();
                }
            }
        };
        closeThread.start();
    }

}
