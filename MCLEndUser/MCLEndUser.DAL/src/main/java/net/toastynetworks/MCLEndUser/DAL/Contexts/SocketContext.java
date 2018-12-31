package net.toastynetworks.MCLEndUser.DAL.Contexts;

import com.google.gson.Gson;
import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.ISocketContext;
import net.toastynetworks.MCLEndUser.Domain.IObserver;
import net.toastynetworks.MCLEndUser.Domain.Modpack;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SocketContext implements ISocketContext {

    public static List<IObserver> observableList = new ArrayList<>();
    static List<Modpack> modpackList = new ArrayList<>();
    ExecutorService threadPool = Executors.newWorkStealingPool();
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();


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
    public void checkStatus() {
        ScheduledFuture<?> scheduledFuture = service.scheduleAtFixedRate(pollable, 0, 30, TimeUnit.SECONDS);
    }

    @Override
    public void setModpackListForStatusCheck(List<Modpack> modpackList) {
        System.out.println("Set modpacks in list to new ones");
        this.modpackList = modpackList;
    }

    @Override
    public void registerObserver(IObserver observer) {
        observableList.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observableList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        System.out.println(observableList.size());
        System.out.println("Notify from Socket Context");
        for (IObserver observer :
                observableList) {
            observer.update(modpackList);
        }
    }

    private Runnable pollable = () -> threadPool.execute(() -> {
        Thread.currentThread().setName("MCL-PollServerStatus-Thread");
        Gson gson = new Gson();
            for (Modpack modpack :
                    modpackList) {
                String modpackJson = gson.toJson(modpack);
                sendMessageToServer(modpackJson);
            }
    });

}
