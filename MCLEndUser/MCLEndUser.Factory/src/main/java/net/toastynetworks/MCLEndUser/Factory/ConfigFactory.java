package net.toastynetworks.MCLEndUser.Factory;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLEnduser.BLL.ConfigLogic;

public class ConfigFactory {

    public static IConfigLogic CreateLogic() {
        return new ConfigLogic();
    }

}
