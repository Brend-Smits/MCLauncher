package net.toastynetworks.MCLAdmin.DAL.Contexts;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackUploadContext;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Domain.UploadedFile;
import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

public class ModpackUploadRestApiContext implements IModpackUploadContext {
    ModpackRestApiContext modpackRestApiContext = new ModpackRestApiContext();
    public void uploadMultipleFiles(ArrayList<File> files, Modpack modpack) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/uploadMultipleFiles")
                    .field("files", files)
                    .asJson();
            //TODO: Make a file model with attributes: file, size, downloadurl etc. This way we can simply return this model and use it to save things such as downloadurl in db.
            ObjectMapper mapper = new ObjectMapper();
            if (jsonResponse.getStatus() != 200) {
                System.out.println("Headers: " + jsonResponse.getHeaders());
                System.out.println("Body: " + jsonResponse.getBody());
                throw new RuntimeException("Failed: HTTP error code : " + jsonResponse.getStatus() + " " + jsonResponse.getStatusText());
            } else {
                UploadedFile file = mapper.readValue(jsonResponse.getBody().getArray().get(0).toString(), UploadedFile.class);
                modpackRestApiContext.EditModpack(modpack, file);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
