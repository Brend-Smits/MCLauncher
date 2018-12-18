package net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.util.List;

public interface IModpackContext {
    List<Modpack> GetAllModpacks();

    void AddModpack(Modpack modpack);

    void EditModpack(Modpack modpack, int nonEditModpackId);

    void DeleteModpack(int id);
}
