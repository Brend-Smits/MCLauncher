package net.toastynetworks.MCLAdmin.Factory;

import net.toastynetworks.MCLAdmin.BLL.ClientLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IClientLogic;
import net.toastynetworks.MCLAdmin.DAL.Contexts.ClientSocketContext;
import net.toastynetworks.MCLAdmin.DAL.Repositories.ClientRepository;

public class ClientFactory {


    public static IClientLogic CreateLogic() {
        return new ClientLogic(
                new ClientRepository(
                        new ClientSocketContext()));
    }
}
