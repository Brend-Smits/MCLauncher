package net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.io.File;
import java.util.ArrayList;

public interface IModpackUploadContext {
    void uploadMultipleFiles(ArrayList<File> files, Modpack modpack);
}
