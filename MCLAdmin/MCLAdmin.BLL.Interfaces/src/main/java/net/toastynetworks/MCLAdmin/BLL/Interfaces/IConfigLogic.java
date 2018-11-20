package net.toastynetworks.MCLAdmin.BLL.Interfaces;


public interface IConfigLogic {
    void CreateConfig();
    String GetWorkSpaceFromConfig();
    void EditConfig(String newWorkspace);
    void DeleteWorkSpaceFromConfig(String modpackName);
}
