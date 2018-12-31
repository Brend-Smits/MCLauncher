package net.toastynetworks.MCLEnduser.BLL;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.ISocketLogic;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.ISocketRepository;
import net.toastynetworks.MCLEndUser.Domain.IObserver;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

public class SocketLogic implements ISocketLogic {

    private ISocketRepository socketRepository;

    public SocketLogic(ISocketRepository socketRepository) {
        this.socketRepository = socketRepository;
    }

    @Override
    public void sendMessageToServer(String message) {
        socketRepository.sendMessageToServer(message);
    }

    @Override
    public void checkStatus() {
        socketRepository.checkStatus();
    }

    @Override
    public void setModpackListForStatusCheck(List<Modpack> modpackList) {
        socketRepository.setModpackListForStatusCheck(modpackList);
    }

    @Override
    public void registerObserver(IObserver observer) {
        socketRepository.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        socketRepository.unregisterObserver(observer);
    }

    @Override
    public void notifyObserver() {
        System.out.println("Notifying from Socket Logic");
        socketRepository.notifyObserver();
    }
}
