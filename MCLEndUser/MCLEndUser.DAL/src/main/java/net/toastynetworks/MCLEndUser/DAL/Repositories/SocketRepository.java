package net.toastynetworks.MCLEndUser.DAL.Repositories;

import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.ISocketContext;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.ISocketRepository;
import net.toastynetworks.MCLEndUser.Domain.IObserver;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

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
    public void checkStatus() {
        socketContext.checkStatus();
    }

    @Override
    public void setModpackListForStatusCheck(List<Modpack> modpackList) {
        socketContext.setModpackListForStatusCheck(modpackList);
    }

    @Override
    public void registerObserver(IObserver observer) {
        socketContext.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        socketContext.unregisterObserver(observer);
    }

    @Override
    public void notifyObserver() {
        System.out.println("Notify from Socket Repository");
        socketContext.notifyObserver();
    }
}
