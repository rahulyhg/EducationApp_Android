package APIResponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import APIObject.MyPaperListObj;

/**
 * Created by User on 2/21/2018.
 */

public class MyExamresponse {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    ArrayList<MyPaperListObj> MyPaperList;
    @SerializedName("error")
    String error;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<MyPaperListObj> getMyPaperList() {
        return MyPaperList;
    }

    public void setMyPaperList(ArrayList<MyPaperListObj> myPaperList) {
        MyPaperList = myPaperList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
