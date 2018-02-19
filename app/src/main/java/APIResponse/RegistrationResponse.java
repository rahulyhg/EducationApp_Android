package APIResponse;

import com.google.gson.annotations.SerializedName;

import APIObject.SignUpObject;

/**
 * Created by User on 2/19/2018.
 */

public class RegistrationResponse {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    SignUpObject result;
    @SerializedName("error")
    String error;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public SignUpObject getResult() {
        return result;
    }

    public void setResult(SignUpObject result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
