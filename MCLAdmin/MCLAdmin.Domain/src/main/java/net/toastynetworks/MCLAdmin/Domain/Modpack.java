package net.toastynetworks.MCLAdmin.Domain;

public class Modpack {

    private String name;
    private String versionType;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Modpack(String name, String versionType) {
        this.name = name;
        this.versionType = versionType;
    }

    public Modpack() { }

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
