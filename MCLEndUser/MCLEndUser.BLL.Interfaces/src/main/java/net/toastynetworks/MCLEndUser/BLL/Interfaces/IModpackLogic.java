package net.toastynetworks.MCLEndUser.BLL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.io.IOException;
import java.util.ArrayList;

public interface IModpackLogic {
    ArrayList<Modpack> GetAllModpacks();

    void downloadFile(String fileURL, String saveDir) throws IOException;
}
