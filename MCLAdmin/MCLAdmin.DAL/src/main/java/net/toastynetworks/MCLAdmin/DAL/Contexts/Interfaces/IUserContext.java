package net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.User;

public interface IUserContext {

    User Login(String username, String password);

}
