package net.toastynetworks.MCLAdmin.Factory;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.BLL.ModpackUploadLogic;
import net.toastynetworks.MCLAdmin.DAL.Contexts.ModpackUploadRestApiContext;
import net.toastynetworks.MCLAdmin.DAL.Repositories.ModpackUploadRepository;

public class ModpackUploadFactory {

    public static IModpackUploadLogic CreateLogic() {
        return new ModpackUploadLogic(new ModpackUploadRepository(new ModpackUploadRestApiContext()));
    }

}
