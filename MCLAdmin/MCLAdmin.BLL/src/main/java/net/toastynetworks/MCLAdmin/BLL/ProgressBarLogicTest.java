package net.toastynetworks.MCLAdmin.BLL;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ProgressBarLogicTest {
    String directory = "tests/Progressbar";

    /**
     * See if the percentage of the progress bar is set correctly
     */
    @Test
    void calculateProgressCheckPercentage() {
        File file = new File(directory);
        ProgressBarLogic progressBarLogic = new ProgressBarLogic(file);
        for (int i= 0; i<3; i++) {
            progressBarLogic.progress.add(String.valueOf(i));
            progressBarLogic.calculateProgress(progressBarLogic.progress);
        }
            Assert.assertTrue(progressBarLogic.progressPercentage == 1);
    }

    /**
     * Test to see if the file number is set correctly, it should be equal to 3 (2 files and 1 directory)
     */
    @Test
    void calculateProgressCheckTotalFileNumber() {
        File file = new File(directory);
        ProgressBarLogic progressBarLogic = new ProgressBarLogic(file);
        Assert.assertTrue(progressBarLogic.totalFileNumber == 3);
    }
}