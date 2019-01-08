package net.toastynetworks.MCLEndUser.DAL.Repositories;

import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.IUserContext;
import net.toastynetworks.MCLEndUser.DAL.Interfaces.IUserRepository;
import net.toastynetworks.MCLEndUser.Domain.User;

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
