package cn.edu.hit.sms.entity.user;

public class Student extends User {
    private String major;

    public Student(String id, String name, String gender, int userType, String major) {
        super(id, name, gender, userType);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
