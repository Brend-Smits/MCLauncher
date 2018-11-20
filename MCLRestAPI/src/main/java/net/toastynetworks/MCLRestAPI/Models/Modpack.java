package net.toastynetworks.MCLRestAPI.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Modpack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String modpackName;
    private String modpackVersionType;

    public Modpack( int modpackId, String name, String versionType) {
        this.id = modpackId;
        this.modpackName = name;
        this.modpackVersionType = versionType;
    }

    public Modpack() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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