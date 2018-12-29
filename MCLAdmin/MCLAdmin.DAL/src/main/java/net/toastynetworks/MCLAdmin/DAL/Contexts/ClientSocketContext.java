package net.toastynetworks.MCLAdmin.DAL.Contexts;

import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IClientSocketContext;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class ClientSocketContext implements IClientSocketContext {
    ;
    ExecutorService threadPool = Executors.newWorkStealingPool();

    public ClientSocketContext() {
    }

    @Override
    public void message(String message) {
        threadPool.execute(() -> {
            URI uri = URI.create("ws://localhost:8095/wstest/");
            try {
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                try {
                    // Attempt Connect
                    Session session = container.connectToServer(EventClientSocket.class, uri);
                    // Send a message
                    session.getBasicRemote().sendText(message);
                    // Close session
                    Thread.sleep(10000);
                    session.close();
                } finally {
                    // Force lifecycle stop when done with container.
                    // This is to free up threads and resources that the
                    // JSR-356 container allocates. But unfortunately
                    // the JSR-356 spec does not handle lifecycles (yet)
                    if (container instanceof LifeCycle) {
                        ((LifeCycle) container).stop();
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace(System.err);
            }
        });
    }

}