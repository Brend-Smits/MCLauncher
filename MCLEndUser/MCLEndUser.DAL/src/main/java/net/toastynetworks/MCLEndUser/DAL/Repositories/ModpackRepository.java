package net.toastynetworks.MCLEndUser.DAL.Repositories;

import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.IModpackRepository;

import java.util.List;

public class ModpackRepository implements IModpackRepository {

    private IModpackContext modpackContext;


    public ModpackRepository(IModpackContext modpackRestContext) {
        modpackContext = modpackRestContext;
    }

    public List<String> GetAllModpacks() {
        return modpackContext.GetAllModpacks();
    }
}
