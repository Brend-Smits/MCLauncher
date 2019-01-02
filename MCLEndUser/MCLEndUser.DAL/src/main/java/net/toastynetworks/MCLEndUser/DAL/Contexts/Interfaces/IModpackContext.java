package net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IModpackContext {

    ArrayList<Modpack> GetAllModpacks();

    void downloadFile(String fileURL, String saveDir) throws IOException;
}
