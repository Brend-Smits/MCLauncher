package net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLEndUser.Domain.User;

public interface IUserContext {

    User Login(String username, String password);

}
