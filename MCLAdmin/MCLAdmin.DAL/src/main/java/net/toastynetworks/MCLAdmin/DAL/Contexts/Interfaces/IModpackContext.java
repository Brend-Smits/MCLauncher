package net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.util.List;

public interface IModpackContext {

    List<String> GetAllModpackNames();
    List<String> GetAllModpackVersions();

    List<Modpack> GetAllModpacks();

    void AddModpack(Modpack modpack);
}
