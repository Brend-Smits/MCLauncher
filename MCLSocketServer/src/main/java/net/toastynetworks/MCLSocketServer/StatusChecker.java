package net.toastynetworks.MCLSocketServer;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class StatusChecker {

    public StatusChecker() {
    }

    public boolean checkStatusOf(String host, int port) throws IOException {
        boolean open = true;
        Socket socket = SocketFactory.getDefault().createSocket();
        try {
            socket.setSoTimeout(5000);
            socket.connect(new InetSocketAddress(host, port));
            socket.close();
        } catch (ConnectException e) {
            open = false;
            System.err.println(e);
        } catch (Exception e) {
            open = false;
            System.err.println(e);
        }
        return open;
    }
}
