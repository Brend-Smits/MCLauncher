package net.toastynetworks.MCLAdmin.BLL.Interfaces;

import java.util.List;

public interface IModpackLogic {

    List<String> GetAllModpackNames();
    List<String> GetAllModpackVersions();

}
