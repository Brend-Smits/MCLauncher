package net.toastynetworks.MCLAdmin.Factory;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.BLL.ModpackLogic;
import net.toastynetworks.MCLAdmin.DAL.Contexts.ModpackMemoryContext;
import net.toastynetworks.MCLAdmin.DAL.Contexts.ModpackRestApiContext;
import net.toastynetworks.MCLAdmin.DAL.Repositories.ModpackRepository;

public class ModpackFactory {

    public static IModpackLogic CreateLogic() {
        return new ModpackLogic(new ModpackRepository(new ModpackRestApiContext()));
    }

    public static IModpackLogic CreateLogicTests() {
        return new ModpackLogic(new ModpackRepository(new ModpackMemoryContext()));
    }

}
