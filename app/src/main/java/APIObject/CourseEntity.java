package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/20/2018.
 */

public class CourseEntity {
    @SerializedName("userId")
    String userid;
    @SerializedName("type")
    int type;

    public CourseEntity(String userid, int type) {
        this.userid = userid;
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
