package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.Domain.Modpack;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.zeroturnaround.zip.ZipException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ModpackUploadLogicTest {
    Modpack modpack;
    String workspace = "C:\\Users\\Brend\\CloudStation\\School\\Semester-3\\Individueel\\MCLauncher\\MCLAdmin\\MCLAdmin.BLL\\tests";

    @Before
    public void setUp() throws Exception {
        this.modpack =  new Modpack("TestModpack", "Release", " testmodpack.test.com");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void prepareUpload() {
    }

    @Test
    public void createZipArchiveSuccessful() {
        //Arrange
        ModpackUploadLogic modpackUploadLogic = new ModpackUploadLogic();
        String uploadDirectory = workspace + "/" + modpack.getId() + "-" + modpack.getName() + "/";
        File dir = new File(uploadDirectory);
        File returnedFile = null;
        try {
            //Act
             returnedFile = modpackUploadLogic.createZipArchive(dir, modpack.getName(), workspace);
            System.out.println(returnedFile.getPath());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        //Assert
         Assert.assertTrue(returnedFile.exists());
        try {
            Files.deleteIfExists(returnedFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createZipArchiveFailed() {
        //Arrange
        ModpackUploadLogic modpackUploadLogic = new ModpackUploadLogic();
        String uploadDirectory = workspace + "/" + 2 + "-" + modpack.getName() + "/";
        File dir = new File(uploadDirectory);
        File returnedFile = null;
        try {
            //Act
            returnedFile = modpackUploadLogic.createZipArchive(dir, modpack.getName(), workspace);
            System.out.println(returnedFile.getPath());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assertions.assertThrows(ZipException.class);
    }

    @Test
    public void deleteZipAndFiles() {
    }
}
