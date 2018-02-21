package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/21/2018.
 */

public class UpdateExamEntity {
    @SerializedName("course")
    int course;
    @SerializedName("exam")
    String exam;
    @SerializedName("shortDesciption")
    String shortDesciption;
    @SerializedName("university")
    String university;
    @SerializedName("userId")
    String userId;

    public UpdateExamEntity(int course, String exam, String shortDesciption, String university, String userId) {
        this.course = course;
        this.exam = exam;
        this.shortDesciption = shortDesciption;
        this.university = university;
        this.userId = userId;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getShortDesciption() {
        return shortDesciption;
    }

    public void setShortDesciption(String shortDesciption) {
        this.shortDesciption = shortDesciption;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
