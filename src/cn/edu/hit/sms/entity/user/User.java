package cn.edu.hit.sms.entity.user;

public class User {
    private String id;
    private String name;
    private String gender;
    private int userType;

    public User(String id, String name, String gender, int userType) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return String.format("id: %s\nname: %s\ngender: %s\nuserType: %d", id, name, gender, userType);
    }
}
