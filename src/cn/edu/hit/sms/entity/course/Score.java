package cn.edu.hit.sms.entity.course;

import cn.edu.hit.sms.entity.user.Student;

public class Score {
    private Student student;
    private Course course;
    private int score;

    public Score(Student student, Course course, int score) {
        this.student = student;
        this.course = course;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
