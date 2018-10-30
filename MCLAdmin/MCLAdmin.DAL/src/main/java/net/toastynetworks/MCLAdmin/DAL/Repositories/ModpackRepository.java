package net.toastynetworks.MCLAdmin.DAL.Repositories;

import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackRepository;

import java.util.List;

public class ModpackRepository implements IModpackRepository {

    private IModpackContext modpackContext;

    public ModpackRepository(IModpackContext modpackRestContext) {
        modpackContext = modpackRestContext;
    }

    public List<String> GetAllModpackNames() {
        return null;
    }

    public List<String> GetAllModpackVersions() {
        return null;
    }
}
