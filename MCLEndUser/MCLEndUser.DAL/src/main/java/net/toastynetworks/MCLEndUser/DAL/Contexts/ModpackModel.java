package net.toastynetworks.MCLEndUser.DAL.Contexts;

public class ModpackModel {
    private String name;
    private String versionType;

    public ModpackModel(String name, String versionType) {
        this.name = name;
        this.versionType = versionType;
    }

    public ModpackModel() {

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