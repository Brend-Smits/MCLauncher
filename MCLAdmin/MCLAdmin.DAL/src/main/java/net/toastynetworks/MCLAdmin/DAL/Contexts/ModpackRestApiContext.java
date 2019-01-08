package net.toastynetworks.MCLAdmin.DAL.Contexts;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
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
            Modpack modpackObject = new Modpack(modpack.getName(), modpack.getVersionType(), modpack.getHost());

            makeUnirestRequest(modpackObject, Unirest.post("http://localhost:8080/v1/modpack/addModpack"), "Failed: HTTP error code : ");

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public List<Modpack> GetModpackList() {
        try {
            HttpResponse<Modpack[]> modpackListResponse = Unirest.get("http://localhost:8080/v1/modpack")
                    .header("Authorization", UserRestApiContext.JwtToken)
                    .asObject(Modpack[].class);
            Modpack[] modpackObjectArray = modpackListResponse.getBody();
            return Arrays.asList(modpackObjectArray);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void EditModpack(Modpack modpack, int nonEditModpackId) {
        try {
            makeUnirestRequest(modpack, Unirest.put("http://localhost:8080/v1/modpack/" + nonEditModpackId), "Failed: HTTP error code: ");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EditModpack(Modpack modpack, UploadedFile file, int nonEditModpackId) {
        try {
            modpack.setDownloadUrl(file.getFileDownloadUri());
            makeUnirestRequest(modpack, Unirest.put("http://localhost:8080/v1/modpack/" + nonEditModpackId), "Failed: HTTP error code: ");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DeleteModpack(int id) {
        try {
            HttpResponse<String> deleteModpack = Unirest.delete("http://localhost:8080/v1/modpack/" + id)
                    .header("Authorization", UserRestApiContext.JwtToken)
                    .asString();
            if (deleteModpack.getStatus() != 200) {
                throw new RuntimeException("Failed: HTTP error code: " + deleteModpack.getStatus() + " " + deleteModpack.getStatusText());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void makeUnirestRequest(Object object, HttpRequestWithBody methodWithUrl, String errorDescription) throws UnirestException {
        HttpResponse<JsonNode> updateModpack = methodWithUrl
                .header("Content-Type", "application/json")
                .header("Authorization", UserRestApiContext.JwtToken)
                .body(object)
                .asJson();
        if (updateModpack.getStatus() != 200) {
            throw new RuntimeException(errorDescription + updateModpack.getStatus() + " " + updateModpack.getStatusText());
        }
    }
}
