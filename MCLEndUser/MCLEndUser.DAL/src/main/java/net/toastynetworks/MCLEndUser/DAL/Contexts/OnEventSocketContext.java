package net.toastynetworks.MCLEndUser.DAL.Contexts;

import com.google.gson.Gson;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import javax.websocket.*;

import static net.toastynetworks.MCLEndUser.DAL.Contexts.SocketContext.modpackList;


@ClientEndpoint
public class OnEventSocketContext {
    Gson gson = new Gson();
    SocketContext socketContext = new SocketContext();

    @OnOpen
    public void onWebSocketConnect() {
        System.out.println("[Connected]");
    }
    @OnMessage
    public void onWebSocketText(String message) {
        System.out.println("[Received]: " + message);
        Modpack newModpack = gson.fromJson(message, Modpack.class);

        if (modpackList.stream().anyMatch(o -> o.getId() == newModpack.getId())) {
            Modpack oldModpack = modpackList.stream().filter(o -> o.getId() == newModpack.getId()).findFirst().get();
//            if (modpackList.stream().filter(o -> o.getId() == newModpack.getId()).anyMatch(b -> b.isOnline() != newModpack.isOnline()))
            if (oldModpack.isOnline() != newModpack.isOnline()) {
                modpackList.remove(oldModpack);
                modpackList.add(newModpack);
                socketContext.notifyObserver();
            } else {
                System.out.println("Modpack server status has not changed!");
            }
        } else {
            modpackList.add(newModpack);
            socketContext.notifyObserver();
        }

    }
    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("[Closed]: " + reason);
    }
    @OnError
    public void onWebSocketError(Throwable cause) {
        System.out.println("[ERROR]: " + cause.getMessage());
    }

}
