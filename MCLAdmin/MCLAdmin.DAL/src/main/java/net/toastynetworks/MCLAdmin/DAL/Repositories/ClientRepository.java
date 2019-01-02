package net.toastynetworks.MCLAdmin.DAL.Repositories;

import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IClientSocketContext;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IClientRepository;

public class ClientRepository implements IClientRepository {

    private IClientSocketContext clientSocketContext;

    public ClientRepository(IClientSocketContext clientSocketContext) {
        this.clientSocketContext = clientSocketContext;
    }

    @Override
    public void message(String message) {
        clientSocketContext.message(message);
    }
}
