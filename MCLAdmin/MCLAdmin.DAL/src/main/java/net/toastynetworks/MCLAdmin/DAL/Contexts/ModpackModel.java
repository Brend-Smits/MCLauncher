package net.toastynetworks.MCLAdmin.DAL.Contexts;

public class ModpackModel {
    private String modpackName;
    private String modpackVersionType;

    public ModpackModel(String name, String versionType) {
        this.modpackName = name;
        this.modpackVersionType = versionType;
    }

    public ModpackModel() {

    }

    public String getName() {
        return modpackName;
    }

    public void setName(String name) {
        this.modpackName = name;
    }

    public String getVersionType() {
        return modpackVersionType;
    }

    public void setVersionType(String versionType) {
        this.modpackVersionType = versionType;
    }

}