package cn.edu.hit.edusys.dao.impl;

import cn.edu.hit.edusys.dao.CourseDao;
import cn.edu.hit.edusys.entity.course.Course;
import cn.edu.hit.edusys.entity.course.Score;

import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public Course getCourseByCid(String cid) {
        return null;
    }

    @Override
    public Course getCourseByCname(String cname) {
        return null;
    }

    @Override
    public List<Course> getCourseByTid(String tid) {
        return null;
    }

    @Override
    public List<Course> getCourseByTname(String tname) {
        return null;
    }

    @Override
    public int addCourse(Course course) {
        return 0;
    }

    @Override
    public int modifyCourse(Course course) {
        return 0;
    }

    @Override
    public int removeCourse(String id) {
        return 0;
    }

    @Override
    public List<Score> getAllScores() {
        return null;
    }

    @Override
    public List<Score> getScoreBySid(String sid) {
        return null;
    }

    @Override
    public List<Score> getScoreBySname(String sname) {
        return null;
    }

    @Override
    public List<Score> getScoreByCid(String cid) {
        return null;
    }

    @Override
    public List<Score> getScoreByCname(String cname) {
        return null;
    }

    @Override
    public int addScore(Score Score) {
        return 0;
    }

    @Override
    public int modifyScore(Score score) {
        return 0;
    }

    @Override
    public int removeScore(Score Score) {
        return 0;
    }
}
