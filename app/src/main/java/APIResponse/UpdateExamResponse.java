package APIResponse;

import com.google.gson.annotations.SerializedName;

import APIObject.ExamIdObj;

/**
 * Created by User on 2/21/2018.
 */

public class UpdateExamResponse {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    ExamIdObj examIdObj;
    @SerializedName("error")
    String error;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ExamIdObj getExamIdObj() {
        return examIdObj;
    }

    public void setExamIdObj(ExamIdObj examIdObj) {
        this.examIdObj = examIdObj;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
