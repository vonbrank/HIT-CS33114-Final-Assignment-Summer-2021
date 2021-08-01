package cn.edu.hit.edusys.dao;

import cn.edu.hit.edusys.entity.course.Course;
import cn.edu.hit.edusys.entity.course.Score;

import java.util.List;

public interface CourseDao {
    public List<Course> getAllCourses();

    public Course getCourseByCid(String cid);

    public Course getCourseByCname(String cname);

    public List<Course> getCourseByTid(String tid);

    public List<Course> getCourseByTname(String tname);

    public int addCourse(Course course);

    public int modifyCourse(Course course);

    public int removeCourse(String id);


    public List<Score> getAllScores();

    public List<Score> getScoreBySid(String sid);

    public List<Score> getScoreBySname(String sname);

    public List<Score> getScoreByCid(String cid);

    public List<Score> getScoreByCname(String cname);

    public int addScore(Score Score);

    public int modifyScore(Score score);

    public int removeScore(Score Score);
}
