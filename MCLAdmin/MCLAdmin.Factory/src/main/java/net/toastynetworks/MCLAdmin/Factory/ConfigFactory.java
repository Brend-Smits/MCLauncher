package net.toastynetworks.MCLAdmin.Factory;

import net.toastynetworks.MCLAdmin.BLL.ConfigLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.BLL.ModpackLogic;
import net.toastynetworks.MCLAdmin.DAL.Contexts.ModpackRestApiContext;
import net.toastynetworks.MCLAdmin.DAL.Repositories.ModpackRepository;

public class ConfigFactory {

    public static IConfigLogic CreateLogic(){
        return new ConfigLogic();
    }

}
