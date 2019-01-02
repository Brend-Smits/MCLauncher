package net.toastynetworks.MCLAdmin.BLL.Interfaces;

import net.toastynetworks.MCLAdmin.Domain.IObservable;
import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.io.File;
import java.util.ArrayList;

public interface IModpackUploadLogic extends IObservable {

    void uploadMultipleFiles(ArrayList<File> files, Modpack modpack);

    void uploadModpack(Modpack modpack, String workspace);

    void resetUploadProgress();
    //TODO: Implement observer pattern for upload file status
    String getUploadStatus();

}
