package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.Domain.Modpack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ModpackUploadLogicTest {
    Modpack modpack;
    String workspace = "tests";
    ModpackUploadLogic modpackUploadLogic = new ModpackUploadLogic();

    @Before
    public void setUp() throws Exception {
        this.modpack =  new Modpack("TestModpack", "Release", " testmodpack.test.com");
    }

    @Test
    public void createZipArchiveSuccessful() {
        //Arrange
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
        String uploadDirectory = workspace + "/" + 2 + "-" + modpack.getName() + "/";
        File dir = new File(uploadDirectory);
        File returnedFile = null;
        try {
            //Act
            returnedFile = modpackUploadLogic.createZipArchive(dir, modpack.getName(), workspace);
            System.out.println(returnedFile.getPath());
            Assert.fail();
        } catch (Exception e) {
        }
    }

}
