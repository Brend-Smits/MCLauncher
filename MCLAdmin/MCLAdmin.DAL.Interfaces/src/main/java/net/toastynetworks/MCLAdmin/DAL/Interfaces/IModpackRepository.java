package net.toastynetworks.MCLAdmin.DAL.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.util.List;

public interface IModpackRepository {

    List<String> GetAllModpackNames();
    List<String> GetAllModpackVersions();

    List<Modpack> GetAllModpacks();

    void AddModpack(Modpack modpack);
}
