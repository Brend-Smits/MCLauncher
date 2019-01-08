package net.toastynetworks.MCLEndUser.BLL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.User;

public interface IUserLogic {

    User Login(String username, String password);
}
