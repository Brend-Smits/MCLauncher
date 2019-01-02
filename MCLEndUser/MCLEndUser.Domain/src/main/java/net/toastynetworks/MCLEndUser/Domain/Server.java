package net.toastynetworks.MCLEndUser.Domain;

public class Server {

    private String host;
    private boolean online;

    public Server(String host, boolean online) {
        this.host = host;
        this.online = online;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

}
