package net.toastynetworks.MCLEndUser.DAL.Contexts;

import com.google.gson.Gson;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import javax.websocket.*;

import static net.toastynetworks.MCLEndUser.DAL.Contexts.SocketContext.checkedStatusModpack;

@ClientEndpoint
public class OnEventSocketContext {
    Gson gson = new Gson();

    @OnOpen
    public void onWebSocketConnect() {
        System.out.println("[Connected]");
    }
    @OnMessage
    public void onWebSocketText(String message) {
        System.out.println("[Received]: " + message);
        Modpack modpack = gson.fromJson(message, Modpack.class);
        checkedStatusModpack = modpack;
        System.out.println("Modpack set with ID: " + modpack.getId() + " with online status: " + modpack.isOnline());


//        Gson gson = new Gson();
//        Modpack modpack = gson.fromJson(message, Modpack.class);
//        System.out.println("[Received]: " + modpack.isOnline());
//        if (isOnlineMap.containsKey(modpack.getId())) {
//            isOnlineMap.replace(modpack.getId(), modpack.isOnline());
//            System.out.println("Replaced!");
//        }
//        isOnlineMap.put(modpack.getId(), modpack.isOnline());
//        System.out.println("Added!");
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
