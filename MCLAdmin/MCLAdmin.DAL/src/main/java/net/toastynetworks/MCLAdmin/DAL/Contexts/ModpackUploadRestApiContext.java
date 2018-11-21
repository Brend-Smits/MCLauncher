package net.toastynetworks.MCLAdmin.DAL.Contexts;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackUploadContext;

import java.io.File;

public class ModpackUploadRestApiContext implements IModpackUploadContext {
    public void uploadSingleFile(File file) {
        //Do something
        System.out.println(file.getAbsolutePath());
        try {
            
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/uploadFile")
                    .header("Content-Type", "multipart/form-data")
                    .field(file.getName(), file)
                    .asJson();

            if (jsonResponse.getStatus() != 200) {
                System.out.println("Headers: " + jsonResponse.getHeaders());
                System.out.println("Body: " + jsonResponse.getBody());
                throw new RuntimeException("Failed: HTTP error code : " + jsonResponse.getStatus() + " " + jsonResponse.getStatusText());
            } else {
                System.out.println(jsonResponse.getBody());
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void uploadMultipleFiles(File[] files) {
        //Do something
    }
}
