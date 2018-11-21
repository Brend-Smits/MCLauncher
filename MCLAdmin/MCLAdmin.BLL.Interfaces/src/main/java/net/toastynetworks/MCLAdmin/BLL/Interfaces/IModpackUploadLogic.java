package net.toastynetworks.MCLAdmin.BLL.Interfaces;

import java.io.File;

public interface IModpackUploadLogic {

    void uploadSingleFile(File file);
    void uploadMultipleFiles(File[] files);
}
