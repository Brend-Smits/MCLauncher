package net.toastynetworks.MCLAdmin.BLL.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.io.File;
import java.util.ArrayList;

public interface IModpackUploadLogic {

    void uploadSingleFile(File file);
    void uploadMultipleFiles(ArrayList<File> files);
    void uploadModpack(Modpack modpack, String workspace);
}
