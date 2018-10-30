package net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces;

import java.util.List;

public interface IModpackContext {

    List<String> GetAllModpackNames();
    List<String> GetAllModpackVersions();
}
