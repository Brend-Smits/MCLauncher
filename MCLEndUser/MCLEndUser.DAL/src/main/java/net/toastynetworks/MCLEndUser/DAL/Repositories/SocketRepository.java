package net.toastynetworks.MCLEndUser.DAL.Repositories;

import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.ISocketContext;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.ISocketRepository;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

public class SocketRepository implements ISocketRepository {

    private ISocketContext socketContext;

    public SocketRepository(ISocketContext socketContext) {
        this.socketContext = socketContext;
    }

    @Override
    public void sendMessageToServer(String message) {
        socketContext.sendMessageToServer(message);
    }

    @Override
    public Modpack checkStatus(String modpackJson) {
        return socketContext.checkStatus(modpackJson);
    }
}
