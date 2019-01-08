package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IUserLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IUserRepository;
import net.toastynetworks.MCLAdmin.Domain.User;

public class UserLogic implements IUserLogic{

    private IUserRepository userRepository;

    public UserLogic(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User Login(String username, String password) {
        return userRepository.Login(username, password);
    }
}
