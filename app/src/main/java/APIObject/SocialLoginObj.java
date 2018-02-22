package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/22/2018.
 */

public class SocialLoginObj {
    @SerializedName("socialId")
    String socialId;
    @SerializedName("socialType")
    String socialType;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("profilePic")
    String profilePic;
    @SerializedName("deviceToken")
    String deviceToken;
    @SerializedName("deviceType")
    String deviceType;

    public SocialLoginObj(String socialId, String socialType, String name, String email, String profilePic, String deviceToken, String deviceType) {
        this.socialId = socialId;
        this.socialType = socialType;
        this.name = name;
        this.email = email;
        this.profilePic = profilePic;
        this.deviceToken = deviceToken;
        this.deviceType = deviceType;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
