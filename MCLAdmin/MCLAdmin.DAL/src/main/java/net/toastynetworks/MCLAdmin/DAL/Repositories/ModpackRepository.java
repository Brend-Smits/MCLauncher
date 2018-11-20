package net.toastynetworks.MCLAdmin.DAL.Repositories;

import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackRepository;
import net.toastynetworks.MCLAdmin.Domain.Modpack;

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

    public List<Modpack> GetAllModpacks() {

        return modpackContext.GetAllModpacks();
    }

    public void AddModpack(Modpack modpack) {
        modpackContext.AddModpack(modpack);
    }
    public void EditModpack(Modpack modpack) {
        modpackContext.EditModpack(modpack);
    }
    public void DeleteModpack(int id) {
        modpackContext.DeleteModpack(id);
    }
}
