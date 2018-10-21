package net.toastynetworks.MCLEndUser.DAL.Contexts;

import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.IModpackContext;

public class ModpackRestApiContext implements IModpackContext {
    public String GetAllModpacks() {
        //Get the modpacks here
        return "Modpack";
    }
}
