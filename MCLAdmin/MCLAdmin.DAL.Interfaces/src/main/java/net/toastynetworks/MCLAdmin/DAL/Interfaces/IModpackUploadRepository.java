package net.toastynetworks.MCLAdmin.DAL.Interfaces;

import java.io.File;

public interface IModpackUploadRepository {
    void uploadSingleFile(File file);
    void uploadMultipleFiles(File[] files);
}
