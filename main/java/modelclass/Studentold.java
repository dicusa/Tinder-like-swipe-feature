package modelclass;

public class Studentold {

    String rollno;
    String name;
    String imageUrl;


    public Studentold() {
    }


    public Studentold(String rollno, String name, String imageUrl) {
        this.rollno = rollno;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
