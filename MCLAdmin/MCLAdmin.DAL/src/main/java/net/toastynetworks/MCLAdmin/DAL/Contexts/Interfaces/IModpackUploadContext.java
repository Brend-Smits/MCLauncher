package net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces;

import java.io.File;

public interface IModpackUploadContext {
    void uploadSingleFile(File file);
    void uploadMultipleFiles(File[] files);
}
