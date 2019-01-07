package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackUploadRepository;
import net.toastynetworks.MCLAdmin.Domain.IObservable;
import net.toastynetworks.MCLAdmin.Domain.IObserver;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipException;
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

    public ModpackUploadLogic() {

    }

    /**
     * Upload a file to the REST API
     * @param files Arraylist of files should be given
     * @param modpack The modpack that the file is associated with
     */
    @Override
    public void uploadMultipleFiles(ArrayList<File> files, Modpack modpack) {
        uploadStatus = "Uploading file(s) to server!";
        modpackUploadRepository.uploadMultipleFiles(files, modpack);
    }

    /**
     * Upload a modpack using the REST API.
     * @param modpack
     * @param workspace
     */
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

    /**
     * Reset the upload progress, used after an upload is done.
     */
    @Override
    public void resetUploadProgress() {
        ProgressBarLogic.resetProgress();
    }

    /**
     * Check what the current status is with uploading. This will not return a number, but rather a string of the file it's currently working on.
     * @return String uploadStatus
     */
    @Override
    public String getUploadStatus() {
        return uploadStatus;
    }

    /**
     * Prepare file upload and create zip archive of files that need uploading.
     * @param modpack modpack we are uploading
     * @param workspace workspace of the user
     * @return ArrayList<File>
     * @throws Exception if something goes wrong with creating zip or such.
     */
    protected ArrayList<File> prepareUpload(Modpack modpack, String workspace) throws Exception {
        String uploadDirectory = workspace + "/" + String.valueOf(modpack.getId()) + "-" + modpack.getName() + "/";
        File dir = new File(uploadDirectory);
        ArrayList<File> files = new ArrayList<>();
        if (dir != null) {
            File zipToUpload = createZipArchive(dir, modpack.getName(), workspace);
            files.add(zipToUpload);
        }
        return files;
    }

    /**
     * Create a zip archive of the given directory. Hierachy of the files will stay in tact.
     * @param directoryToZip Directory to zip should be given
     * @param modpackName The modpack-name, used to the zip properly
     * @return File - Returns the zipped folder as a file
     * @throws Exception When file can not be found or other file issues
     */
    protected File createZipArchive(File directoryToZip, String modpackName, String workspace) throws Exception, ZipException {
        zipName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + "--" + modpackName + ".zip";
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
        } catch (ZipException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e);
        }
        return zipFile;
    }

    /**
     * Delete the archive created earlier in the upload process
     * @throws IOException
     */
    protected void deleteZipAndFiles() throws IOException {
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
