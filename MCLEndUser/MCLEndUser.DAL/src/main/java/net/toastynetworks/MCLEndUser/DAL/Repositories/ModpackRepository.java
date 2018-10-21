package net.toastynetworks.MCLEndUser.DAL.Repositories;

import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.IModpackRepository;

public class ModpackRepository implements IModpackRepository {

    private IModpackContext modpackContext;


    public ModpackRepository(IModpackContext modpackRestContext) {
        modpackContext = modpackRestContext;
    }

    public String GetAllModpacks() {
        return modpackContext.GetAllModpacks();
    }
}
