package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IClientLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IClientRepository;

public class ClientLogic implements IClientLogic{

    private IClientRepository clientRepository;

    public ClientLogic(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public void message(String message) {
        clientRepository.message(message);
    }
}
