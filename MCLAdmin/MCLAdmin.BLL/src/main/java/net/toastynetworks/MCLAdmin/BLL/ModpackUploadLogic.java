package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackUploadRepository;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModpackUploadLogic implements IModpackUploadLogic {
    private IModpackUploadRepository modpackUploadRepository;
    private String zipName;
    private String workspace;

    public ModpackUploadLogic(IModpackUploadRepository modpackUploadRepo) {
        modpackUploadRepository = modpackUploadRepo;
    }

    @Override
    public void uploadMultipleFiles(ArrayList<File> files, Modpack modpack) {
        modpackUploadRepository.uploadMultipleFiles(files, modpack);
    }

    public void uploadModpack(Modpack modpack, String workspace) {
        try {
            this.workspace = workspace;
            String uploadDirectory = workspace + "/" + String.valueOf(modpack.getId()) + "-" + modpack.getName() + "/";
            File dir = new File(uploadDirectory);
            ArrayList<File> files = new ArrayList<>();
            if (dir != null) {
                File zipToUpload = createZipArchive(dir, modpack);
                files.add(zipToUpload);
            }
            uploadMultipleFiles(files, modpack);
            deleteZipAndFiles();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    private File createZipArchive(File directoryToZip, Modpack modpack) throws Exception {
        zipName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + "--" + modpack.getName() + ".zip";
        File zipFile = new File(workspace + "\\" +  zipName);
        try {
            ZipUtil.pack(directoryToZip, zipFile);
        } catch (Exception e) {
            System.out.println(e);
        }
        return zipFile;
    }

    private void deleteZipAndFiles() throws IOException {
        FileUtils.deleteDirectory(new File(workspace + "\\" +  zipName.replace(".zip", "")));
        FileUtils.forceDelete(new File(workspace + "\\" + zipName));
        System.out.println("Done!");
    }
}
