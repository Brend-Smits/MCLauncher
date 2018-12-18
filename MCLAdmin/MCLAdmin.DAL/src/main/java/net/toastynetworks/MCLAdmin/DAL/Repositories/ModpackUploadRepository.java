package net.toastynetworks.MCLAdmin.DAL.Repositories;

import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackUploadContext;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackUploadRepository;
import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.io.File;
import java.util.ArrayList;

public class ModpackUploadRepository implements IModpackUploadRepository {
    private IModpackUploadContext modpackContext;

    public ModpackUploadRepository(IModpackUploadContext modpackCont) {
        modpackContext = modpackCont;
    }

    public void uploadMultipleFiles(ArrayList<File> files, Modpack modpack) {
        modpackContext.uploadMultipleFiles(files, modpack);
    }
}
