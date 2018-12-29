package net.toastynetworks.MCLAdmin.Domain;

public class Modpack {

    private int id;
    private String name;
    private String versionType;
    private String downloadUrl;
    private String host;
    private boolean isOnline;


    public Modpack(String name, String versionType, String host) {
        this.name = name;
        this.versionType = versionType;
        this.host = host;
    }

    public Modpack() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

}
