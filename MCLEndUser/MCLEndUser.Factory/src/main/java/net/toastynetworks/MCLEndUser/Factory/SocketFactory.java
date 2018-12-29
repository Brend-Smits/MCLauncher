package net.toastynetworks.MCLEndUser.Factory;

import net.toastynetworks.MCLEndUser.BLL.Interfaces.ISocketLogic;
import net.toastynetworks.MCLEndUser.DAL.Contexts.SocketContext;
import net.toastynetworks.MCLEndUser.DAL.Repositories.SocketRepository;
import net.toastynetworks.MCLEnduser.BLL.SocketLogic;

public class SocketFactory {

    public static ISocketLogic CreateLogic() {
        return new SocketLogic(new SocketRepository(new SocketContext()));
    }

}
