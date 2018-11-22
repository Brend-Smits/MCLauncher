package net.toastynetworks.MCLAdmin.DAL.Interfaces;

import java.io.File;
import java.util.ArrayList;

public interface IModpackUploadRepository {
    void uploadSingleFile(File file);
    void uploadMultipleFiles(ArrayList<File> files);
}
