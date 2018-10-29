package net.toastynetworks.MCLAdmin.DAL.Interfaces;

import java.util.List;

public interface IModpackRepository {

    List<String> GetAllModpackNames();
    List<String> GetAllModpackVersions();
}
