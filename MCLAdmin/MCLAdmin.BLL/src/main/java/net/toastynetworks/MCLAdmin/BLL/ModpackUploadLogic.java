package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackUploadRepository;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

import static net.toastynetworks.zip.ZipUtils.addDirToZipArchive;
import static net.toastynetworks.zip.ZipUtils.unzipArchive;

public class ModpackUploadLogic implements IModpackUploadLogic {
    private IModpackUploadRepository modpackUploadRepository;
    private IConfigLogic configLogic;
    private String zipName;
    private String workspace = configLogic.GetWorkSpaceFromConfig() + "\\";

    public ModpackUploadLogic(IModpackUploadRepository modpackUploadRepo) {
        modpackUploadRepository = modpackUploadRepo;
    }


    @Override
    public void uploadSingleFile(File file) {
        modpackUploadRepository.uploadSingleFile(file);
    }

    @Override
    public void uploadMultipleFiles(ArrayList<File> files) {
        modpackUploadRepository.uploadMultipleFiles(files);
    }

    public void uploadModpack(Modpack modpack) {
        try {
            String uploadDirectory = configLogic.GetWorkSpaceFromConfig() + "/" + String.valueOf(modpack.getId()) + "-" + modpack.getName() + "/";
            File dir = new File(uploadDirectory);
            System.out.println(dir);
            ArrayList<File> files = new ArrayList<>();
            if (dir != null) {
                zipName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + "--" + modpack.getName() + ".zip";
                File zip = new File(workspace + zipName);
                FileOutputStream fileOutputStream = new FileOutputStream(zip);
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
                addDirToZipArchive(zipOutputStream, new File(dir.toString()), null);
                zipOutputStream.flush();
                zipOutputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                files.add(zip);
            }
            uploadMultipleFiles(files);
            unzipAndCleanArchive(modpack);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void unzipAndCleanArchive(Modpack modpack) {
        String folderName = String.valueOf(modpack.getId()) + "-" + modpack.getName();
        try {
            unzipArchive(workspace + zipName);
            moveFilesToWorkspace(folderName);
            deleteZipAndFiles();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void deleteZipAndFiles() throws IOException {
        FileUtils.deleteDirectory(new File(workspace + zipName.replace(".zip", "")));
        FileUtils.forceDelete(new File(workspace + zipName));
        System.out.println("Done!");
    }

    private void moveFilesToWorkspace(String folderName) throws IOException {
        File src = new File(workspace + zipName.replace(".zip", "") + "\\" + folderName);
        File dest = new File(workspace + folderName);
        FileUtils.copyDirectory(src, dest);
        System.out.println("Done!!");
    }
}
