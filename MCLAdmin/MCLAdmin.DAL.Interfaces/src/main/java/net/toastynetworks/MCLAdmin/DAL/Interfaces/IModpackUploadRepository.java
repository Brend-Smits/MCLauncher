package net.toastynetworks.MCLAdmin.DAL.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.io.File;
import java.util.ArrayList;

public interface IModpackUploadRepository {
    void uploadMultipleFiles(ArrayList<File> files, Modpack modpack);
}
