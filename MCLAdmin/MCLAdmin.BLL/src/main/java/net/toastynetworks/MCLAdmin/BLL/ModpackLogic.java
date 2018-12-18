package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackRepository;
import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.util.List;

public class ModpackLogic implements IModpackLogic {
    private IModpackRepository modpackRepository;

    public ModpackLogic(IModpackRepository modpackRepo) {
        modpackRepository = modpackRepo;
    }

    public List<Modpack> GetAllModpacks() {
        return modpackRepository.GetAllModpacks();
    }

    public void AddModpack(Modpack modpack) {
        modpackRepository.AddModpack(modpack);
    }

    public void EditModpack(Modpack modpack, int nonEditModpackId) {
        modpackRepository.EditModpack(modpack, nonEditModpackId);
    }

    public void DeleteModpack(int id) {
        modpackRepository.DeleteModpack(id);
    }
}
