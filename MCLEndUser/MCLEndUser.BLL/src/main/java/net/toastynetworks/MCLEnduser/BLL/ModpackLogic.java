package net.toastynetworks.MCLEnduser.BLL;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.IModpackRepository;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

public class ModpackLogic implements IModpackLogic {

    private IModpackRepository modpackRepository;

    public ModpackLogic(IModpackRepository modpackRepo) {
        modpackRepository = modpackRepo;
    }

    public List<Modpack> GetAllModpacks() {
        return modpackRepository.GetAllModpacks();
    }

    @Override
    public void DownloadModpack(String fileUri) {
        modpackRepository.DownloadModpack(fileUri);
    }
}
