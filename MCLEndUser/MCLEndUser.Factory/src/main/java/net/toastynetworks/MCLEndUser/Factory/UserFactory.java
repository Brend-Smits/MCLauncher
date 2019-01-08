package net.toastynetworks.MCLEndUser.Factory;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.IUserLogic;
import net.toastynetworks.MCLEndUser.DAL.Contexts.UserRestApiContext;
import net.toastynetworks.MCLEndUser.DAL.Repositories.UserRepository;
import net.toastynetworks.MCLEnduser.BLL.UserLogic;

public class UserFactory {

    public static IUserLogic CreateLogic() {
        return new UserLogic(
                new UserRepository(
                        new UserRestApiContext()));
    }
}
