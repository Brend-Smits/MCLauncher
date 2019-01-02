package net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.IObservable;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

public interface ISocketContext extends IObservable {

    void sendMessageToServer(String message);

    void checkStatus();

    void setModpackListForStatusCheck(List<Modpack> modpackList);
}
