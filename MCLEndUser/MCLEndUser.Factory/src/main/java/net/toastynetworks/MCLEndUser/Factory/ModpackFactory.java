package net.toastynetworks.MCLEndUser.Factory;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLEndUser.DAL.Contexts.ModpackRestApiContext;
import net.toastynetworks.MCLEndUser.DAL.Repositories.ModpackRepository;
import net.toastynetworks.MCLEnduser.BLL.ModpackLogic;

public class ModpackFactory {

    public static IModpackLogic CreateLogic() {
        return new ModpackLogic(new ModpackRepository(new ModpackRestApiContext()));
    }
}
