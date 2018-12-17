package net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

public interface IModpackContext {

    List<Modpack> GetAllModpacks();
    void DownloadModpack(String fileUri);
}
