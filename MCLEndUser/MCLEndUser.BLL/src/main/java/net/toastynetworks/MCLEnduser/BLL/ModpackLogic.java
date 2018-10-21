package net.toastynetworks.MCLEnduser.BLL;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.IModpackRepository;

public class ModpackLogic implements IModpackLogic {

    private IModpackRepository modpackRepository;

    public ModpackLogic(IModpackRepository modpackRepo) {
        modpackRepository = modpackRepo;
    }

    public String GetAllModpacks() {
        return modpackRepository.GetAllModpacks();
    }
}
