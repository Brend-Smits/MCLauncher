package net.toastynetworks.MCLEndUser.DAL.Contexts;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import net.toastynetworks.MCLEndUser.DAL.Contexts.Interfaces.IUserContext;
import net.toastynetworks.MCLEndUser.Domain.User;

public class UserRestApiContext implements IUserContext {

    public static String JwtToken;


    @Override
    public User Login(String username, String password) {
        User user = new User(username, password);
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/api/auth/signin")
                    .header("Content-Type", "application/json")
                    .body(user)
                    .asJson();
            user.setAccessToken(jsonResponse.getBody().getObject().getString("accessToken"));
            user.setTokenType(jsonResponse.getBody().getObject().getString("tokenType"));
            JwtToken = user.getTokenType() + " " + user.getAccessToken();
            if (jsonResponse.getStatus() != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + jsonResponse.getStatus() + " " + jsonResponse.getStatusText());
            }

        } catch (Exception exception) {
            System.out.println(exception);
            return user;
        }
        return user;
    }
}
