package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/20/2018.
 */

public class ForgotEntity {
    @SerializedName("email")
    String email;

    public ForgotEntity(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
