package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/20/2018.
 */

public class ExamImageObj {
    @SerializedName("id")
    int id;
    @SerializedName("exmimg")
    String exmimg;

    public ExamImageObj(int id, String exmimg) {
        this.id = id;
        this.exmimg = exmimg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExmimg() {
        return exmimg;
    }

    public void setExmimg(String exmimg) {
        this.exmimg = exmimg;
    }
}
