package cn.edu.hit.sms.dao;

import cn.edu.hit.sms.entity.course.Course;
import cn.edu.hit.sms.entity.course.Score;

import java.util.List;

public interface CourseDao {
    public List<Course> getAllCourses();

    public Course getCourseByCid(String cid);

    public Course getCourseByCname(String cname);

    public List<Course> getCourseByTid(String tid);

    public List<Course> getCourseByTname(String tname);

    public int getNumberOfStudentByCid(String cid);

    public int addCourse(Course course);

    public int modifyCourse(Course course);

    public int removeCourse(String id);

    public int getNumberOfCourseByTid(String tid);

    public List<Score> getAllScores();

    public List<Score> getScoreBySid(String sid);

    public List<Score> getScoreBySname(String sname);

    public List<Score> getScoreByCid(String cid);

    public List<Score> getScoreByCname(String cname);

    public int getScoreBySidAndCid(String sid, String cid);

    public int getScoreBySnameAndCname(String sname, String cname);

    public int addScore(Score Score);

    public int modifyScore(Score score);

    public int removeScore(Score Score);
}
