package net.toastynetworks.MCLEndUser.DAL.Contexts;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLEndUser.Domain.Modpack;
import net.toastynetworks.unirest.UnirestObjectMapperUtils;
import org.zeroturnaround.zip.ZipUtil;
import org.zeroturnaround.zip.commons.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ModpackRestApiContext implements IModpackContext {
    UnirestObjectMapperUtils unirestObjectMapperUtils = new UnirestObjectMapperUtils();

    @Override
    public ArrayList<Modpack> GetAllModpacks() {
        try {
            HttpResponse<Modpack[]> modpackListResponse = Unirest.get("http://localhost:8080/v1/modpack").asObject(Modpack[].class);
            Modpack[] modpackObjectArray = modpackListResponse.getBody();
            ArrayList<Modpack> modpackArray = new ArrayList<>();
            for (Modpack modpack :
                    modpackObjectArray) {
                modpackArray.add(modpack);
            }
            return modpackArray;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * Downloads a file from a URL
     *
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    @Override
    public void downloadFile(String fileURL, String saveDir) throws IOException {
        int BUFFER_SIZE = 4096;
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            Path path = Paths.get(saveDir);
            if (Files.notExists(path)) {
                Files.createDirectory(path);
            }
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
            System.out.println("I will now extract it");
            ZipUtil.unpack(new File(saveFilePath), new File(saveDir));
            System.out.println("I will now delete the zip");
            FileUtils.forceDelete(new File(saveFilePath));
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}
