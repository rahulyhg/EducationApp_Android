package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/19/2018.
 */

public class LoginEntity {
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("deviceType")
    String deviceType;
    @SerializedName("deviceToken")
    String deviceToken;

    public LoginEntity(String email, String password, String deviceType, String deviceToken) {
        this.email = email;
        this.password = password;
        this.deviceType = deviceType;
        this.deviceToken = deviceToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
