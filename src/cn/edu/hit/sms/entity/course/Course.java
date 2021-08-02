package cn.edu.hit.sms.entity.course;

import cn.edu.hit.sms.entity.user.Teacher;

public class Course {
    private String id;
    private String name;
    private Teacher teacher;
    private int numOfStu;

    public Course(String id, String name, Teacher teacher, int numOfStu) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.numOfStu = numOfStu;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getNumOfStu() {
        return numOfStu;
    }

    public void setNumOfStu(int numOfStu) {
        this.numOfStu = numOfStu;
    }
}
