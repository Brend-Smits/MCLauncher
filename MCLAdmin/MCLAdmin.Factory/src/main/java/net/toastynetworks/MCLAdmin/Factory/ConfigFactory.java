package net.toastynetworks.MCLAdmin.Factory;

import net.toastynetworks.MCLAdmin.BLL.ConfigLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;

public class ConfigFactory {

    public static IConfigLogic CreateLogic(){
        return new ConfigLogic();
    }

}
