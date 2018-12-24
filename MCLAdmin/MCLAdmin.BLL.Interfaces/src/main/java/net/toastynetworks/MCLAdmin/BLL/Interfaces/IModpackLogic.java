package net.toastynetworks.MCLAdmin.BLL.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.util.List;

public interface IModpackLogic {

    List<Modpack> GetAllModpacks();

    void AddModpack(Modpack modpack);

    void EditModpack(Modpack modpack, int nonEditModpackId);

    void DeleteModpack(int id);
}
