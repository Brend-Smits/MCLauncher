package net.toastynetworks.MCLAdmin.BLL.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.User;

public interface IUserLogic {

    User Login(String username, String password);
}
