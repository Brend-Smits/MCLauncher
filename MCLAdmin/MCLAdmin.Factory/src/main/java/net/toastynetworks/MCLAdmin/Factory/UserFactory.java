package net.toastynetworks.MCLAdmin.Factory;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IUserLogic;
import net.toastynetworks.MCLAdmin.BLL.UserLogic;
import net.toastynetworks.MCLAdmin.DAL.Contexts.UserRestApiContext;
import net.toastynetworks.MCLAdmin.DAL.Repositories.UserRepository;

public class UserFactory {

    public static IUserLogic CreateLogic() {
        return new UserLogic(
                new UserRepository(
                        new UserRestApiContext()));
    }
}
