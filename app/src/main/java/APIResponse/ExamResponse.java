package APIResponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import APIObject.ExamListObj;

/**
 * Created by User on 2/20/2018.
 */

public class ExamResponse {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    ArrayList<ExamListObj> examList;
    @SerializedName("error")
    String error;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<ExamListObj> getExamList() {
        return examList;
    }

    public void setExamList(ArrayList<ExamListObj> examList) {
        this.examList = examList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
