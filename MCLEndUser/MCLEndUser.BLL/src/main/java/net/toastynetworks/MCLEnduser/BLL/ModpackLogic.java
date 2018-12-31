package net.toastynetworks.MCLEnduser.BLL;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.IModpackRepository;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModpackLogic implements IModpackLogic {

    private IModpackRepository modpackRepository;

    public ModpackLogic(IModpackRepository modpackRepo) {
        modpackRepository = modpackRepo;
    }

    public ArrayList<Modpack> GetAllModpacks() {
        return modpackRepository.GetAllModpacks();
    }

    @Override
    public void downloadFile(String fileURL, String saveDir) throws IOException {
        modpackRepository.downloadFile(fileURL, saveDir);
    }

}
