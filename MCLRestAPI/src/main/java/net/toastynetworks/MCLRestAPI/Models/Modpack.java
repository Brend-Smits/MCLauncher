package net.toastynetworks.MCLRestAPI.Models;

public class Modpack {
    private String modpackName;
    private String modpackVersionType;

    public Modpack(String name, String versionType) {
        this.modpackName = name;
        this.modpackVersionType = versionType;
    }

    public Modpack() {

    }

    public String getModpackName() {
        return modpackName;
    }

    public void setModpackName(String modpackName) {
        this.modpackName = modpackName;
    }

    public String getModpackVersionType() {
        return modpackVersionType;
    }

    public void setModpackVersionType(String modpackVersionType) {
        this.modpackVersionType = modpackVersionType;
    }

}