package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackUploadRepository;
import net.toastynetworks.MCLAdmin.Domain.IObservable;
import net.toastynetworks.MCLAdmin.Domain.IObserver;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ModpackUploadLogic implements IModpackUploadLogic, IObservable {
    private List<IObserver> observableList = new ArrayList<>();
    private IModpackUploadRepository modpackUploadRepository;
    private String zipName;
    private String workspace;
    private String uploadStatus;

    public ModpackUploadLogic(IModpackUploadRepository modpackUploadRepo) {
        modpackUploadRepository = modpackUploadRepo;
    }

    @Override
    public void uploadMultipleFiles(ArrayList<File> files, Modpack modpack) {
        uploadStatus = "Uploading file(s) to server!";
        modpackUploadRepository.uploadMultipleFiles(files, modpack);
    }

    public void uploadModpack(Modpack modpack, String workspace) {
        try {
            this.workspace = workspace;
            ArrayList<File> files = prepareUpload(modpack, workspace);
            uploadMultipleFiles(files, modpack);
            deleteZipAndFiles();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void resetUploadProgress() {
        ProgressBarLogic.resetProgress();
    }

    @Override
    public String getUploadStatus() {
        return uploadStatus;
    }

    private ArrayList<File> prepareUpload(Modpack modpack, String workspace) throws Exception {
        String uploadDirectory = workspace + "/" + String.valueOf(modpack.getId()) + "-" + modpack.getName() + "/";
        File dir = new File(uploadDirectory);
        ArrayList<File> files = new ArrayList<>();
        if (dir != null) {
            File zipToUpload = createZipArchive(dir, modpack);
            files.add(zipToUpload);
        }
        return files;
    }

    private File createZipArchive(File directoryToZip, Modpack modpack) throws Exception {
        zipName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + "--" + modpack.getName() + ".zip";
        uploadStatus = "Creating Zipfile from Directory";
        File zipFile = new File(workspace + "\\" + zipName);
        try {
            ProgressBarLogic progressBarLogic = new ProgressBarLogic(directoryToZip, zipFile);
            ZipUtil.pack(directoryToZip, zipFile, name -> {
                uploadStatus = "Adding file: " + name + " to the zip";
                progressBarLogic.progressPercentage = 0;
                progressBarLogic.progress.add(name);
                progressBarLogic.calculateProgress(progressBarLogic.progress);
                notifyObserver();
                return name;
            });
            System.out.println("\rDone-Uploading now                                                 \n");
        } catch (Exception e) {
            System.out.println(e);
        }
        return zipFile;
    }

    private void deleteZipAndFiles() throws IOException {
        FileUtils.deleteDirectory(new File(workspace + "\\" + zipName.replace(".zip", "")));
        FileUtils.forceDelete(new File(workspace + "\\" + zipName));
        uploadStatus = "Cleaning up :)";
        System.out.println("Done!");
    }

    @Override
    public void registerObserver(IObserver observer) {
        observableList.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observableList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (IObserver observer :
                observableList) {
            observer.update(ProgressBarLogic.progressPercentage);
        }
    }
}
