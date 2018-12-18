package net.toastynetworks.MCLEndUser.DAL.Repositories;

import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.IModpackRepository;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.io.IOException;
import java.util.List;

public class ModpackRepository implements IModpackRepository {

    private IModpackContext modpackContext;


    public ModpackRepository(IModpackContext modpackRestContext) {
        modpackContext = modpackRestContext;
    }

    public List<Modpack> GetAllModpacks() {
        return modpackContext.GetAllModpacks();
    }

    @Override
    public void downloadFile(String fileURL, String saveDir) throws IOException {
        modpackContext.downloadFile(fileURL, saveDir);
    }
}
