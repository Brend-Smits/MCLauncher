package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackUploadRepository;

import java.io.File;
import java.util.ArrayList;

public class ModpackUploadLogic implements IModpackUploadLogic {
    private IModpackUploadRepository modpackUploadRepository;

    public ModpackUploadLogic(IModpackUploadRepository modpackUploadRepo) {
        modpackUploadRepository = modpackUploadRepo;
    }


    @Override
    public void uploadSingleFile(File file) {
        modpackUploadRepository.uploadSingleFile(file);
    }

    @Override
    public void uploadMultipleFiles(ArrayList<File> files) {
        modpackUploadRepository.uploadMultipleFiles(files);
    }
}
