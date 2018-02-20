package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/20/2018.
 */

public class EditProfileEntity {
    @SerializedName("name")
    String name;
    @SerializedName("dateOfBirth")
    String dateOfBirth;
    @SerializedName("university")
    String university;

    public EditProfileEntity(String name, String dateOfBirth, String university) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
