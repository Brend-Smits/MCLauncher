package net.toastynetworks.MCLAdmin.DAL.Contexts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackUploadContext;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Domain.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ModpackUploadRestApiContext implements IModpackUploadContext {
    ModpackRestApiContext modpackRestApiContext = new ModpackRestApiContext();

    public void uploadMultipleFiles(ArrayList<File> files, Modpack modpack) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/uploadMultipleFiles")
                    .field("files", files)
                    .asJson();

            if (jsonResponse.getStatus() != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + jsonResponse.getStatus() + " " + jsonResponse.getStatusText());
            }
            updateModpackWithFileInfo(modpack, jsonResponse);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void updateModpackWithFileInfo(Modpack modpack, HttpResponse<JsonNode> responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //Get first object of the array that is returned, this is the file we uploaded.
            UploadedFile file = mapper.readValue(responseBody.getBody().getArray().get(0).toString(), UploadedFile.class);
            modpackRestApiContext.EditModpack(modpack, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
