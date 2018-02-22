package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/19/2018.
 */

public class SignUpObject {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("dateOfBirth")
    String dateOfBirth;
    @SerializedName("lastLogin")
    String lastLogin;
    @SerializedName("universityId")
    int universityId;
    @SerializedName("university")
    String university;
    @SerializedName("views")
    String views;
    @SerializedName("menberFrom")
    String menberFrom;
    @SerializedName("profilePic")
    String profilePic;
    @SerializedName("loginType")
    String loginType;

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getMenberFrom() {
        return menberFrom;
    }

    public void setMenberFrom(String menberFrom) {
        this.menberFrom = menberFrom;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
