package net.toastynetworks.MCLEndUser.DAL.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.User;

public interface IUserRepository {

    User Login(String username, String password);

}
