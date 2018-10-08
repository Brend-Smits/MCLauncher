package net.toastynetworks.MCLauncher.API.Models;

public class Modpack {
    private String name;
    private String versionType;

    public Modpack(String name, String versionType) {
        this.name = name;
        this.versionType = versionType;
    }

    public Modpack() {

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

}
