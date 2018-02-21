package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/21/2018.
 */

public class UserIdObj {
    @SerializedName("userId")
    String userId;

    public UserIdObj(String userId) {
        this.userId = userId;
    }
}
