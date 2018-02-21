package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/21/2018.
 */

public class HistObj {
    @SerializedName("examId")
    int examId;
    @SerializedName("userId")
    String userId;

    public HistObj(int examId, String userId) {
        this.examId = examId;
        this.userId = userId;
    }
}
