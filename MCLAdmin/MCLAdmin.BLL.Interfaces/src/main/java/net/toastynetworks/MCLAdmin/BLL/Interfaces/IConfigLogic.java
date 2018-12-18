package net.toastynetworks.MCLAdmin.BLL.Interfaces;


import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.util.List;

public interface IConfigLogic {
    void CreateConfig();

    String GetWorkSpaceFromConfig();

    void EditConfig(String newWorkspace);

    void DeleteWorkSpaceFromConfig(String modpackName);

    void PrepareWorkspace(List<Modpack> modpacks);
}
