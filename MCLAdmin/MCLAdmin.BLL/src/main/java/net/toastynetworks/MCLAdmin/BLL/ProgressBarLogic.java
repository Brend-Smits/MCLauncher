package net.toastynetworks.MCLAdmin.BLL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class ProgressBarLogic {

    public static Set<String> progress = new HashSet<String>();
    public static double progressPercentage;
    private static int totalFileNumber;
    private static File targetDir;
    private static File zipArchive;

    public ProgressBarLogic(File dirToTarget, File zipFile) {
        targetDir = dirToTarget;
        zipArchive = zipFile;
        try {
            totalFileNumber = Math.toIntExact(Files.walk(dirToTarget.toPath())
                    .parallel()
                    .count());
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Calculates the progress based on total number of files
     *
     * @param progress a set where the size designates the number of processed archives
     */
    public static void calculateProgress(Set<String> progress) {

        progressPercentage = progress.size() / (double) totalFileNumber;
    }

    public static void resetProgress() {
        progressPercentage = 0;
    }

}
