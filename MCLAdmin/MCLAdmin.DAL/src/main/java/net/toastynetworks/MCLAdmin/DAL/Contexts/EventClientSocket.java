package net.toastynetworks.MCLAdmin.DAL.Contexts;

import javax.websocket.*;

@ClientEndpoint
public class EventClientSocket {
    @OnOpen
    public void onWebSocketConnect() {
        System.out.println("[Connected]");
    }
    @OnMessage
    public void onWebSocketText(String message) {
        System.out.println("[Received]: " + message);
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
