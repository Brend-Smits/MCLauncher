package net.toastynetworks.MCLEnduser.BLL;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.IUserLogic;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.IUserRepository;
import net.toastynetworks.MCLEndUser.Domain.User;

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
