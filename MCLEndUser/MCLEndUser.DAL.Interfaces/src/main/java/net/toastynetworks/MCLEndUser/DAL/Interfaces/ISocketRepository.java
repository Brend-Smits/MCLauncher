package net.toastynetworks.MCLEndUser.DAL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.IObservable;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

public interface ISocketRepository extends IObservable {

    void sendMessageToServer(String message);

    void checkStatus();

    void setModpackListForStatusCheck(List<Modpack> modpackList);
}
