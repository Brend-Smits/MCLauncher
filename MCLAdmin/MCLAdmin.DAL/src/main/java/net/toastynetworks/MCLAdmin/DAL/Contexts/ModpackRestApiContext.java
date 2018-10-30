package net.toastynetworks.MCLAdmin.DAL.Contexts;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackContext;

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

    public String GetJSONFromUrl(String endpoint) {
        try {
            URL url = new URL("http://localhost:8080/" + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
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



        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public List<String> GetModpackNamesFromJSON(String json) {
        List<String> modpackNames = new ArrayList<String>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            ModpackModel[] modpackModels = objectMapper.readValue(json, ModpackModel[].class);
            for (ModpackModel model : modpackModels) {
                System.out.println(model.getName());
                modpackNames.add(model.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modpackNames;
    }

}
