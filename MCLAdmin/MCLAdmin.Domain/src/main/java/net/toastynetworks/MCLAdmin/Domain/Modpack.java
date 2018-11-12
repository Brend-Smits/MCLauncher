package net.toastynetworks.MCLAdmin.Domain;

public class Modpack {

    private String modpackName;
    private String modpackVersionType;
    private int modpackId;

    public int getModpackId() {
        return modpackId;
    }

    public void setModpackId(int modpackId) {
        this.modpackId = modpackId;
    }

    public Modpack(String name, String versionType) {
        this.modpackName = name;
        this.modpackVersionType = versionType;
    }

    public Modpack(int modpackId, String name, String versionType) {
        this.modpackId = modpackId;
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
