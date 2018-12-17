package net.toastynetworks.MCLEndUser.BLL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

public interface IModpackLogic {
    List<Modpack> GetAllModpacks();
    void DownloadModpack(String fileUri);
}
