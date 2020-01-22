
package modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Student {

    @SerializedName("student")
    @Expose
    private List<Student_> student = null;

    public List<Student_> getStudent() {
        return student;
    }

    public void setStudent(List<Student_> student) {
        this.student = student;
    }

}
