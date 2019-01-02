package net.toastynetworks.MCLSocketServer;

import com.google.gson.Gson;
import net.toastynetworks.MCLSocketServer.model.Modpack;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;

@ServerEndpoint(value = "/wstest/")
public class EventServerSocket {
    static HashSet<Session> sessions = new HashSet<>();
    StatusChecker statusChecker = new StatusChecker();
    Gson gson = new Gson();
    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[Connected] SessionID:"+session.getId());
        String message = String.format("[New client with client side session ID]: %s", session.getId());
        broadcast(message);
        sessions.add(session);
        System.out.println("[#sessions]: " + sessions.size());
    }
    @OnMessage
    public void onText(String message,Session session) {
        System.out.println("[Session ID] : " + session.getId() + " [Received] : " + message);
        try {
            Modpack modpack = gson.fromJson(message, Modpack.class);
            boolean isOnline = statusChecker.checkStatusOf(modpack.getHost(), 25565);
            modpack.setOnlineStatus(isOnline);
            broadcast(gson.toJson(modpack));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[Socket Closed: " + reason);
        sessions.remove(session);
    }
    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }
    public void broadcast(String s) {
        System.out.println("[Broadcast] { " + s + " } to:");
        for(Session session : sessions) {
            try {
                session.getAsyncRemote().sendText(s);
                System.out.println("\t\t >> Client associated with server side session ID: " + session.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("[End of Broadcast]");
    }
}
