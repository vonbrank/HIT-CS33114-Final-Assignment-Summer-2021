package cn.edu.hit.sms.entity.course;

import cn.edu.hit.sms.entity.user.Student;

public class Score {
//    private Student student;
//    private Course course;
    private int score;
    private String sid;
    private String sname;
    private String cid;
    private String cname;

    public Score(Student student, Course course, int score) {
        this.sid = student.getId();
        this.sname = student.getName();
        this.cid = course.getId();
        this.cname = course.getName();
//        this.student = student;
//        this.course = course;
        this.score = score;
    }

    public Score(String sid, String sname, String cid, String cname, int score) {
        this.score = score;
        this.sid = sid;
        this.sname = sname;
        this.cid = cid;
        this.cname = cname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return String.format("sid: %s\nsname: %s\ncid: %s\ncname: %s\nscore: %d",
                sid, sname, cid, cname, score);
    }
}
