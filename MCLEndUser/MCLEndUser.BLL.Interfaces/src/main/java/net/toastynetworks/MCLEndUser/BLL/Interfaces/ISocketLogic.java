package net.toastynetworks.MCLEndUser.BLL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.util.HashMap;

public interface ISocketLogic {

    void sendMessageToServer(String message);

    Modpack checkStatus(Modpack modpack);
}
