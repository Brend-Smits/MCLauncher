package net.toastynetworks.MCLEndUser.DAL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.io.IOException;
import java.util.ArrayList;

public interface IModpackRepository {
    ArrayList<Modpack> GetAllModpacks();

    void downloadFile(String fileURL, String saveDir) throws IOException;
}
