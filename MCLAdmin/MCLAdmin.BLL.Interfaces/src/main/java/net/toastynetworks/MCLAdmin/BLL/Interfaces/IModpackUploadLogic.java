package net.toastynetworks.MCLAdmin.BLL.Interfaces;

import java.io.File;
import java.util.ArrayList;

public interface IModpackUploadLogic {

    void uploadSingleFile(File file);
    void uploadMultipleFiles(ArrayList<File> files);
}
