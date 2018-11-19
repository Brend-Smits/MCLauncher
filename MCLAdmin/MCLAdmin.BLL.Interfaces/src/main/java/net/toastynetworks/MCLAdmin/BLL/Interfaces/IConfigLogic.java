package net.toastynetworks.MCLAdmin.BLL.Interfaces;

import java.util.HashMap;

public interface IConfigLogic {
    void CreateConfig();
    String GetWorkSpaceFromConfig(String modpackName);
    void EditConfig(String modpackName, String newWorkspace);
    void DeleteWorkSpaceFromConfig(String modpackName);
}
