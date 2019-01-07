package net.toastynetworks.MCLAdmin.DAL.Contexts;

import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IClientSocketContext;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientSocketContext implements IClientSocketContext {
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
                    Session session = container.connectToServer(EventClientSocket.class, uri);
                    session.getBasicRemote().sendText(message);
                    Thread.sleep(10000);
                    session.close();
                } finally {
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