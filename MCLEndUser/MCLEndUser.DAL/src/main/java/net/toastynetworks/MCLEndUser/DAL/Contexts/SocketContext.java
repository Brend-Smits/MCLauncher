package net.toastynetworks.MCLEndUser.DAL.Contexts;

import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.ISocketContext;
import net.toastynetworks.MCLEndUser.Domain.Modpack;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketContext implements ISocketContext {

    ExecutorService threadPool = Executors.newWorkStealingPool();
    static Modpack checkedStatusModpack;

    public SocketContext() {
    }

    @Override
    public void sendMessageToServer(String message) {
        threadPool.execute(() -> {
            URI uri = URI.create("ws://localhost:8095/wstest/");
            try {
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                try {
                    // Attempt Connect
                    Session session = container.connectToServer(OnEventSocketContext.class, uri);
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

    @Override
    public Modpack checkStatus(String modpackJson) {
        sendMessageToServer(modpackJson);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return checkedStatusModpack;
    }
}
