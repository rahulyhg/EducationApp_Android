package APIObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by User on 2/21/2018.
 */

public class MyPaperListObj {
    @SerializedName("id")
    int id;
    @SerializedName("examName")
    String  examName;
    @SerializedName("shortDescription")
    String shortDescription;
    @SerializedName("collageName")
    String collageName;
    @SerializedName("userId")
    int userId;
    @SerializedName("userEmail")
    String userEmail;
    @SerializedName("courseId")
    int courseId;
    @SerializedName("courseName")
    String courseName;
    @SerializedName("approved")
    int approved;
    @SerializedName("examImg")
    ArrayList<ExamImageObj> examImg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getCollageName() {
        return collageName;
    }

    public void setCollageName(String collageName) {
        this.collageName = collageName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public ArrayList<ExamImageObj> getExamImg() {
        return examImg;
    }

    public void setExamImg(ArrayList<ExamImageObj> examImg) {
        this.examImg = examImg;
    }
}
