package cn.edu.hit.sms.entity.user;

public class Teacher extends User {
    private String profession;

    public Teacher(String id, String name, String gender, int userType, String profession) {
        super(id, name, gender, userType);
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
