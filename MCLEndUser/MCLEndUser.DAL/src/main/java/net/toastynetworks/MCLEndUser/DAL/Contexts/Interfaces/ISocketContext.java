package net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

public interface ISocketContext {

    void sendMessageToServer(String message);

    Modpack checkStatus(String modpackJson);
}
