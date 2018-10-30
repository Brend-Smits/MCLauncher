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
    public List<String> GetAllModpackNames() {
        return null;
    }

    public List<String> GetAllModpackVersions() {
        return null;
    }

    public List<Modpack> GetAllModpacks() {
        return modpackRepository.GetAllModpacks();
    }
}
