package net.toastynetworks.MCLEndUser.DAL.Contexts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLEndUser.Domain.Modpack;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ModpackRestApiContext implements IModpackContext {
    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    @Override
    public List<Modpack> GetAllModpacks() {
        try{
            HttpResponse<Modpack[]> modpackListResponse = Unirest.get("http://localhost:8080/v1/modpack").asObject(Modpack[].class);
            Modpack[] modpackObjectArray = modpackListResponse.getBody();
            return Arrays.asList(modpackObjectArray);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public void DownloadModpack(String fileUri) {
        try{
            Unirest.get(fileUri);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
