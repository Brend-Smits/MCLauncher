package net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces;

import java.io.File;
import java.util.ArrayList;

public interface IModpackUploadContext {
    void uploadSingleFile(File file);
    void uploadMultipleFiles(ArrayList<File> files);
}
