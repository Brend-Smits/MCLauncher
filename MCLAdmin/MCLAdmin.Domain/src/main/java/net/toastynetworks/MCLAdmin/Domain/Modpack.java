package net.toastynetworks.MCLAdmin.Domain;

import java.util.List;

public class Modpack {

    private String modpackName;
    private String modpackVersionType;
    private int modpackId;
    private List<String> hashedFileStructureList;

    public List<String> getHashedFileStructureList() {
        return hashedFileStructureList;
    }

    public void setHashedFileStructureList(List<String> hashedFileStructureList) {
        this.hashedFileStructureList = hashedFileStructureList;
    }


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

    public Modpack() { }

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
