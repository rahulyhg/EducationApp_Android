package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/20/2018.
 */

public class ExamSearchEntity {
    @SerializedName("university")
    String university;
    @SerializedName("course")
    int course;
    @SerializedName("exam")
    String exam;

    public ExamSearchEntity(String university, int course, String exam) {
        this.university = university;
        this.course = course;
        this.exam = exam;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
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
}
