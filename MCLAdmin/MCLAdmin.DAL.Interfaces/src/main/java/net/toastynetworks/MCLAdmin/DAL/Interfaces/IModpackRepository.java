package net.toastynetworks.MCLAdmin.DAL.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.util.List;

public interface IModpackRepository {
    List<Modpack> GetAllModpacks();
    void AddModpack(Modpack modpack);
    void EditModpack(Modpack modpack);
    void DeleteModpack(int id);
}
