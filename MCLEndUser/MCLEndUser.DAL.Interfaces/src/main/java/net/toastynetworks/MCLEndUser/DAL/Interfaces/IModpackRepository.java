package net.toastynetworks.MCLEndUser.DAL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

public interface IModpackRepository {
    List<Modpack> GetAllModpacks();
    void DownloadModpack(String fileUri);
}
