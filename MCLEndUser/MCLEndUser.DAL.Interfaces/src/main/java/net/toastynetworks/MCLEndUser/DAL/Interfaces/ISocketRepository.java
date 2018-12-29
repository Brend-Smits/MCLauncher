package net.toastynetworks.MCLEndUser.DAL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

public interface ISocketRepository {

    void sendMessageToServer(String message);

    Modpack checkStatus(String modpackJson);
}
