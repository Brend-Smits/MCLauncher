package net.toastynetworks.MCLAdmin.DAL.Contexts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ModpackRestApiContext implements IModpackContext {
    public List<String> GetAllModpackNames() {
        return null;
    }

    public List<String> GetAllModpackVersions() {
        return null;
    }

    public List<Modpack> GetAllModpacks() {
        String json = GetJSONFromUrl("v1/modpack", "GET");
        return GetModpacksFromJSON(json);
    }

    public void AddModpack(Modpack modpack) {
        //
    }

    public String GetJSONFromUrl(String endpoint, String methodCall) {
        try {
            URL url = new URL("http://localhost:8080/" + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(methodCall);
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            StringBuffer response = new StringBuffer();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            connection.disconnect();
            return response.toString();



        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }
    public List<Modpack> GetModpacksFromJSON(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Modpack[] modpackModels = objectMapper.readValue(json, Modpack[].class);
            List<Modpack> modpackList = new ArrayList<Modpack>();
            for (Modpack modpack :
                    modpackModels) {
                modpackList.add(modpack);
            }
            return modpackList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
