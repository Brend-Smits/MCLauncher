package net.toastynetworks.MCLEndUser.BLL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.IObservable;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.List;

public interface ISocketLogic extends IObservable {

    void sendMessageToServer(String message);

    void checkStatus();

    void setModpackListForStatusCheck(List<Modpack> modpackList);
}
