package eu.execom.todolistgrouptwo.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikola Obradovic on 27-Nov-16.
 */

public class TokenContainerDTO {

    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "TokenContainerDTO{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
