package net.toastynetworks.MCLAdmin.DAL.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.User;

public interface IUserRepository {

    User Login(String username, String password);

}
