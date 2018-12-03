package net.toastynetworks.zip;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static File addDirToZipArchive(String workspace, String zipName, File fileToZip, String parrentDirectoryName) throws Exception {
        File zip = new File(workspace + zipName);
        FileOutputStream fileOutputStream = null;
        ZipOutputStream zos = null;
        FileInputStream fis = null;
        try {
            fileOutputStream = new FileOutputStream(zip);
            zos = new ZipOutputStream(fileOutputStream);
            if (fileToZip == null || !fileToZip.exists()) {
                return null;
            }

            String zipEntryName = fileToZip.getName();
            if (parrentDirectoryName != null && !parrentDirectoryName.isEmpty()) {
                zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
            }

            if (fileToZip.isDirectory()) {
                System.out.println("+" + zipEntryName);
                for (File file : fileToZip.listFiles()) {
                    addDirToZipArchive(workspace, zipName, file, zipEntryName);
                }
            } else {
                System.out.println("   " + zipEntryName);
                byte[] buffer = new byte[1024];
                 fis = new FileInputStream(fileToZip);
                zos.putNextEntry(new ZipEntry(zipEntryName));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                zos.close();
                fis.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(zos);
            IOUtils.closeQuietly(fis);
        }
        return zip;
    }
    //TODO: Cleanup this method and make it better understandable.
    public static void unzipArchive(String zipFile) throws ZipException, IOException {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream dest = null;
        BufferedInputStream is = null;
        try {
            System.out.println(zipFile);
            int BUFFER = 2048;
            File file = new File(zipFile);
            ZipFile zip = new ZipFile(file);
            String newPath = zipFile.substring(0, zipFile.length() - 4);
            new File(newPath).mkdir();
            Enumeration zipFileEntries = zip.entries();

            // Process each entry
            while (zipFileEntries.hasMoreElements())
            {
                // grab a zip file entry
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();
                File destFile = new File(newPath, currentEntry);
                //destFile = new File(newPath, destFile.getName());
                File destinationParent = destFile.getParentFile();

                // create the parent directory structure if needed
                destinationParent.mkdirs();

                if (!entry.isDirectory())
                {
                    is = new BufferedInputStream(zip.getInputStream(entry));
                    int currentByte;
                    // establish buffer for writing file
                    byte data[] = new byte[BUFFER];

                    // write the current file to disk
                    fileOutputStream = new FileOutputStream(destFile);
                    dest = new BufferedOutputStream(fileOutputStream, BUFFER);

                    // read and write until last byte is encountered
                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(dest);
            IOUtils.closeQuietly(is);
        }
    }

}
