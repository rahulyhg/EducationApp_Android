package APIResponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import APIObject.CouserObject;

/**
 * Created by User on 2/20/2018.
 */

public class CoursesResponse {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    ArrayList<CouserObject> result;
    @SerializedName("error")
    String error;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<CouserObject> getResult() {
        return result;
    }

    public void setResult(ArrayList<CouserObject> result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
