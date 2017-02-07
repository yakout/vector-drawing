package server;


import java.net.InetAddress;

/**
 * Created by ahmedyakout on 11/1/16.
 */

/**
 * Server Client, the server will create an instance of this class if it received an "Request" message from a client.
 * @author ahmedyakout
 */
public class ServerClient {
    /**
     * Internet Protocol (IP) address.
     */
    private InetAddress address;
    /**
     * port number.
     */
    private int port;

    public ServerClient(InetAddress clientAddress, int clientPort) {
        address = clientAddress;
        port = clientPort;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
