package net.toastynetworks.MCLAdmin.DAL.Contexts;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Domain.UploadedFile;
import net.toastynetworks.unirest.UnirestObjectMapperUtils;

import java.util.Arrays;
import java.util.List;

public class ModpackRestApiContext implements IModpackContext {

    UnirestObjectMapperUtils unirestObjectMapperUtils = new UnirestObjectMapperUtils();

    public List<Modpack> GetAllModpacks() {
        return GetModpackList();
    }

    public void AddModpack(Modpack modpack) {
        try {
            Modpack modpackObject = new Modpack(modpack.getName(), modpack.getVersionType());

            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/v1/modpack/addModpack")
                    .header("Content-Type", "application/json")
                    .body(modpackObject)
                    .asJson();

            if (jsonResponse.getStatus() != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + jsonResponse.getStatus() + " " + jsonResponse.getStatusText());
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public List<Modpack> GetModpackList() {
        try {
            HttpResponse<Modpack[]> modpackListResponse = Unirest.get("http://localhost:8080/v1/modpack").asObject(Modpack[].class);
            Modpack[] modpackObjectArray = modpackListResponse.getBody();
            return Arrays.asList(modpackObjectArray);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void EditModpack(Modpack modpack) {
        try {
            HttpResponse<JsonNode> updateModpack = Unirest.put("http://localhost:8080/v1/modpack/")
                    .header("Content-Type", "application/json")
                    .body(modpack)
                    .asJson();
            if (updateModpack.getStatus() != 200) {
                throw new RuntimeException("Failed: HTTP error code: " + updateModpack.getStatus() + " " + updateModpack.getStatusText());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EditModpack(Modpack modpack, UploadedFile file) {
        try {
            modpack.setDownloadUrl(file.getFileDownloadUri());
            HttpResponse<JsonNode> updateModpack = Unirest.put("http://localhost:8080/v1/modpack/")
                    .header("Content-Type", "application/json")
                    .body(modpack)
                    .asJson();
            if (updateModpack.getStatus() != 200) {
                throw new RuntimeException("Failed: HTTP error code: " + updateModpack.getStatus() + " " + updateModpack.getStatusText());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DeleteModpack(int id) {
        try {
            HttpResponse<String> deleteModpack = Unirest.delete("http://localhost:8080/v1/modpack/" + id).asString();
            if (deleteModpack.getStatus() != 200) {
                throw new RuntimeException("Failed: HTTP error code: " + deleteModpack.getStatus() + " " + deleteModpack.getStatusText());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
