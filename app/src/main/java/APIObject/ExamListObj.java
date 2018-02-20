package APIObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by User on 2/20/2018.
 */

public class ExamListObj {
    @SerializedName("id")
    int id;
    @SerializedName("examName")
    String examName;
    @SerializedName("shortDescription")
    String shortDescription;
    @SerializedName("collageName")
    String collageName;
    @SerializedName("userId")
    int  examinerId;
    @SerializedName("userEmail")
    String  examinerEmailId;
    @SerializedName("courseId")
    int  courseId;
    @SerializedName("courseName")
    String  courseName;
    @SerializedName("examImg")
    ArrayList<ExamImageObj> examImageObjsList;

    public ExamListObj(int id, String examName, String shortDescription, String collageName, int examinerId, String examinerEmailId, int courseId, String courseName, ArrayList<ExamImageObj> examImageObjsList) {
        this.id = id;
        this.examName = examName;
        this.shortDescription = shortDescription;
        this.collageName = collageName;
        this.examinerId = examinerId;
        this.examinerEmailId = examinerEmailId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.examImageObjsList = examImageObjsList;
    }

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

    public int getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(int examinerId) {
        this.examinerId = examinerId;
    }

    public String getExaminerEmailId() {
        return examinerEmailId;
    }

    public void setExaminerEmailId(String examinerEmailId) {
        this.examinerEmailId = examinerEmailId;
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

    public ArrayList<ExamImageObj> getExamImageObjsList() {
        return examImageObjsList;
    }

    public void setExamImageObjsList(ArrayList<ExamImageObj> examImageObjsList) {
        this.examImageObjsList = examImageObjsList;
    }
}
