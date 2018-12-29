package net.toastynetworks.MCLEnduser.BLL;

import com.google.gson.Gson;
import net.toastynetworks.MCLEndUser.BLL.Interfaces.ISocketLogic;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.ISocketRepository;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

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
    public Modpack checkStatus(Modpack modpack) {
        Gson gson = new Gson();
        return socketRepository.checkStatus(gson.toJson(modpack));
    }
}
