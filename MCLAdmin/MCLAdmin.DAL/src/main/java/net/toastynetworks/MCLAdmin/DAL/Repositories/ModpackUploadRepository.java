package net.toastynetworks.MCLAdmin.DAL.Repositories;

import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackUploadContext;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackUploadRepository;

import java.io.File;

public class ModpackUploadRepository implements IModpackUploadRepository {
    private IModpackUploadContext modpackContext;

    public ModpackUploadRepository(IModpackUploadContext modpackCont) {
        modpackContext = modpackCont;
    }
    public void uploadSingleFile(File file) {
        modpackContext.uploadSingleFile(file);
    }

    public void uploadMultipleFiles(File[] files) {
        modpackContext.uploadMultipleFiles(files);
    }
}
