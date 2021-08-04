package cn.edu.hit.sms.dao.impl;

import cn.edu.hit.sms.dao.*;
import cn.edu.hit.sms.entity.course.*;
import cn.edu.hit.sms.entity.user.*;
import cn.edu.hit.sms.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM courses;";
        List<Course> courseList = new ArrayList<>();
        List<String> teacherIDList = new ArrayList<>();
        Course course;
        String cid, cname, tid, tname;
        int numOfStu;
        Teacher teacher;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                cid = rs.getString("cid");
                cname = rs.getString("cname");
                tid = rs.getString("tid");
                tname = rs.getString("tname");
                course = new Course(cid, cname, null, 0);
                teacherIDList.add(tid);
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0; i<courseList.size(); i++) {
            tid = teacherIDList.get(i);
            courseList.get(i).setTeacher((Teacher) userDao.getById(tid));
        }
        for(Course course1 : courseList) {
            cid = course1.getId();
            course1.setNumOfStu(this.getNumberOfStudentByCid(cid));
        }
        return courseList;
    }

    @Override
    public Course getCourseByCid(String cid) {
        String sql = String.format("SELECT * FROM courses WHERE cid='%s';", cid);
        Course course;
        String cname, tid, tname;
        int numOfStu;
        Teacher teacher;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            rs.next();
            cname = rs.getString("cname");
            tid = rs.getString("tid");
            tname = rs.getString("tname");
            course = new Course(cid, cname, null, 0);
            course.setTeacher((Teacher) userDao.getById(tid));
            course.setNumOfStu(this.getNumberOfStudentByCid(cid));
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Course getCourseByCname(String cname) {
        String sql = String.format("SELECT * FROM courses WHERE cname='%s';", cname);
        Course course;
        String cid, tid, tname;
        int numOfStu;
        Teacher teacher;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            rs.next();
            cid = rs.getString("cid");
            tid = rs.getString("tid");
            tname = rs.getString("tname");
            course = new Course(cid, cname, null, 0);
            course.setTeacher((Teacher) userDao.getById(tid));
            course.setNumOfStu(this.getNumberOfStudentByCid(cid));
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> getCourseByTid(String tid) {
        String sql = String.format("SELECT * FROM courses WHERE tid='%s';", tid);
        List<Course> courseList = new ArrayList<>();
        List<String> teacherIDList = new ArrayList<>();
        Course course;
        String cid, cname, tname;
        int numOfStu;
        Teacher teacher;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                cid = rs.getString("cid");
                cname = rs.getString("cname");
                tname = rs.getString("tname");
                course = new Course(cid, cname, null, 0);
                teacherIDList.add(tid);
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Course course1 : courseList) {
            cid = course1.getId();
            course1.setNumOfStu(this.getNumberOfStudentByCid(cid));
            course1.setTeacher((Teacher) userDao.getById(tid));
        }
        return courseList;
    }

    @Override
    public List<Course> getCourseByTname(String tname) {
        String sql = String.format("SELECT * FROM courses WHERE tname='%s';", tname);
        List<Course> courseList = new ArrayList<>();
        List<String> teacherIDList = new ArrayList<>();
        Course course;
        String cid, cname, tid;
        int numOfStu;
        Teacher teacher;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                cid = rs.getString("cid");
                cname = rs.getString("cname");
                tid = rs.getString("tid");
                course = new Course(cid, cname, null, 0);
                teacherIDList.add(tid);
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Course course1 : courseList) {
            cid = course1.getId();
            course1.setNumOfStu(this.getNumberOfStudentByCid(cid));
            course1.setTeacher((Teacher) userDao.getByName(tname));
        }
        return courseList;
    }

    @Override
    public int getNumberOfStudentByCid(String cid) {
        String sql = String.format("SELECT COUNT(*) FROM scores WHERE cid='%s';", cid);
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return-1;
    }

    @Override
    public int addCourse(Course course) {
        if(this.getCourseByCid(course.getId()) != null) {
            return -1;
        }
        String cid = course.getId();
        String cname = course.getName();
        String tid = course.getTeacher().getId();
        String tname = course.getTeacher().getName();
        String sql = String.format("INSERT INTO courses (cid, cname, tid, tname) VALUES ('%s', '%s', '%s', '%s');",
                cid, cname, tid, tname);
        return DBUtils.executeUpdate(sql);
    }

    @Override
    public int modifyCourse(Course course) {
        if(this.getCourseByCid(course.getId()) == null) {
            return -1;
        }
        String cid = course.getId();
        String cname = course.getName();
        String tid = course.getTeacher().getId();
        String tname = course.getTeacher().getName();
        String sql = String.format("UPDATE courses SET cid='%s', cname='%s', tid='%s', tname='%s' WHERE cid='%s';",
                cid, cname, tid, tname, cid);
        return DBUtils.executeUpdate(sql);
    }

    @Override
    public int removeCourse(String id) {
        if(this.getCourseByCid(id) == null) {
            return -1;
        }
        String sql = String.format("DELETE FROM courses WHERE cid='%s';", id);
        return DBUtils.executeUpdate(sql);
    }

    @Override
    public List<Score> getAllScores() {
        String sql = "SELECT * FROM scores;";
        List<Score> scoreList = new ArrayList<>();
        Score score;
        String sid, cname, sname, cid;
        int scoreValue;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            int i=-1;
            while (rs.next()){
                i++;

                sid = rs.getString("sid");
                sname = rs.getString("sname");
                cid = rs.getString("cid");
                cname = rs.getString("cname");

                scoreValue = rs.getInt("score");
                score = new Score(sid, sname, cid, cname, scoreValue);
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    public List<Score> getScoreBySid(String sid) {
        String sql = String.format("SELECT * FROM scores WHERE sid='%s';", sid);
        List<Score> scoreList = new ArrayList<>();
        Score score;
        String cname, sname, cid;
        int scoreValue;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            int i=-1;
            while (rs.next()){
                i++;

                cname = rs.getString("cname");
                sname = rs.getString("sname");
                cid = rs.getString("cid");
                scoreValue = rs.getInt("score");
                score = new Score(sid, sname, cid, cname, scoreValue);
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    public List<Score> getScoreBySname(String sname) {
        String sql = String.format("SELECT * FROM scores WHERE sname='%s';", sname);
        List<Score> scoreList = new ArrayList<>();
        Score score;
        String sid, cname, cid;
        int scoreValue;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            int i=-1;
            while (rs.next()){
                i++;

                sid = rs.getString("sid");
                cname = rs.getString("cname");
                cid = rs.getString("cid");
                scoreValue = rs.getInt("score");
                score = new Score(sid, sname, cid, cname, scoreValue);
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    public List<Score> getScoreByCid(String cid) {
        String sql = String.format("SELECT * FROM scores WHERE cid='%s';", cid);
        List<Score> scoreList = new ArrayList<>();
        Score score;
        String sid, sname, cname;
        int scoreValue;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            int i=-1;
            while (rs.next()){
                i++;

                sid = rs.getString("sid");
                sname = rs.getString("sname");
                cname = rs.getString("cname");
                scoreValue = rs.getInt("score");
                score = new Score(sid, sname, cid, cname, scoreValue);
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    public List<Score> getScoreByCname(String cname) {
        String sql = String.format("SELECT * FROM scores WHERE cname='%s';", cname);
        List<Score> scoreList = new ArrayList<>();
        Score score;
        String sid, sname, cid;
        int scoreValue;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            int i=-1;
            while (rs.next()){
                i++;

                sid = rs.getString("sid");
                sname = rs.getString("sname");
                cid = rs.getString("cid");
                scoreValue = rs.getInt("score");
                score = new Score(sid, sname, cid, cname, scoreValue);
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    public int getScoreBySidAndCid(String sid, String cid) {
        String sql = String.format("SELECT * FROM scores WHERE sid='%s' AND cid='%s';",
                sid, cid);
        int scoreValue = -1;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                scoreValue = rs.getInt("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreValue;
    }

    public int getScoreBySnameAndCname(String sname, String cname) {
        String sql = String.format("SELECT * FROM scores WHERE sname='%s' AND cname='%s';",
                sname, cname);
        int scoreValue = -1;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                scoreValue = rs.getInt("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreValue;
    }

    @Override
    public int addScore(Score score) {
        if(this.getScoreBySidAndCid(score.getSid(), score.getCid()) != -1) {
            return -1;
        }
        String sid = score.getSid();
        String sname = score.getSname();
        String cid = score.getCid();
        String cname = score.getCname();
        int scoreValue = score.getScore();
        String sql = String.format("INSERT INTO scores (sid, sname, cid, cname, score) VALUES ('%s', '%s', '%s', '%s', %d);",
                sid, sname, cid, cname, scoreValue);
        return DBUtils.executeUpdate(sql);
    }

    @Override
    public int modifyScore(Score score) {
        if(this.getScoreBySidAndCid(score.getSid(), score.getCid()) == -1) {
            return -1;
        }
        String sid = score.getSid();
        String cid = score.getCid();
        int scoreValue = score.getScore();
        String sql = String.format("UPDATE scores SET score='%d' WHERE sid='%s' AND cid='%s'",
                scoreValue, sid, cid);
        return DBUtils.executeUpdate(sql);
    }

    @Override
    public int removeScore(Score score) {
        if(this.getScoreBySidAndCid(score.getSid(), score.getCid()) == -1) {
            return -1;
        }
        String sid = score.getSid();
        String cid = score.getCid();
        int scoreValue = score.getScore();
        String sql = String.format("DELETE FROM scores WHERE sid='%s' AND cid='%s'",
                sid, cid);
        return DBUtils.executeUpdate(sql);
    }
}
