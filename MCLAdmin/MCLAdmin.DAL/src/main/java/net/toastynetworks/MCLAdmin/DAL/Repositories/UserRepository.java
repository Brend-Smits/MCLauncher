package net.toastynetworks.MCLAdmin.DAL.Repositories;

import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IUserContext;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IUserRepository;
import net.toastynetworks.MCLAdmin.Domain.User;

public class UserRepository implements IUserRepository {

    private IUserContext userContext;
    public UserRepository(IUserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public User Login(String username, String password) {
        return userContext.Login(username, password);
    }
}
