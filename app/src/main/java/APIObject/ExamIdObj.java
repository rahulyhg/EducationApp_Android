package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/21/2018.
 */

public class ExamIdObj {
    @SerializedName("examId")
    String examId;

    public ExamIdObj(String examId) {
        this.examId = examId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
