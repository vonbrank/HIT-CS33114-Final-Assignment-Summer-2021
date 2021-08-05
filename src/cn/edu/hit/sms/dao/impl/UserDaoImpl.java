package cn.edu.hit.sms.dao.impl;

import cn.edu.hit.sms.dao.UserDao;
import cn.edu.hit.sms.entity.user.*;
import cn.edu.hit.sms.utils.DBUtils;
import tools.SHA256;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users;";
        List<User> userList = new ArrayList<>();
        User user;
        String id, name, gender, major, profession;
        int userType;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                id = rs.getString("id");
                name = rs.getString("name");
                gender = rs.getString("gender");
                userType = rs.getInt("userType");
                if(userType == 1) {
                    profession = rs.getString("profession");
                    user = new Teacher(id, name, gender, profession);
                }
                else if(userType == 2) {
                    major = rs.getString("major");
                    user = new Student(id, name, gender, major);
                }
                else user = new Staff(id, name, gender);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<Staff> getAllStaffs() {
        String sql = "SELECT * FROM users WHERE userType = 0;";
        List<Staff> staffList = new ArrayList<>();
        Staff staff;
        String id, name, gender;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                id = rs.getString("id");
                name = rs.getString("name");
                gender = rs.getString("gender");
                staff = new Staff(id, name, gender);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        String sql = "SELECT * FROM users WHERE userType = 1;";
        List<Teacher> teacherList = new ArrayList<>();
        Teacher teacher;
        String id, name, gender, profession;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                id = rs.getString("id");
                name = rs.getString("name");
                gender = rs.getString("gender");
                profession = rs.getString("profession");

                teacher = new Teacher(id, name, gender, profession);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM users WHERE userType = 2;";
        List<Student> studentList = new ArrayList<>();
        Student student;
        String id, name, gender, major;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                id = rs.getString("id");
                name = rs.getString("name");
                gender = rs.getString("gender");
                major = rs.getString("major");

                student = new Student(id, name, gender, major);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public User getById(String id) {
        String sql = String.format("SELECT * FROM users WHERE id = '%s';", id);
        User user;
        String name, gender, major, profession;
        int userType;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                name = rs.getString("name");
                gender = rs.getString("gender");
                userType = rs.getInt("userType");
                if(userType == 1) {
                    profession = rs.getString("profession");
                    user = new Teacher(id, name, gender, profession);
                }
                else if(userType == 2) {
                    major = rs.getString("major");
                    user = new Student(id, name, gender, major);
                }
                else user = new Staff(id, name, gender);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByName(String name) {
        String sql = String.format("SELECT * FROM users WHERE name = '%s';", name);
        User user;
        String id, gender, major, profession;
        int userType;
        ResultSet rs = DBUtils.executeQuery(sql);
        try {
            while (rs.next()){
                id = rs.getString("id");
                gender = rs.getString("gender");
                userType = rs.getInt("userType");
                if(userType == 1) {
                    profession = rs.getString("profession");
                    user = new Teacher(id, name, gender, profession);
                }
                else if(userType == 2) {
                    major = rs.getString("major");
                    user = new Student(id, name, gender, major);
                }
                else user = new Staff(id, name, gender);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int add(User user, String pwd) {
        if(this.getById(user.getId()) != null){
            return -1;
        }
        String id, name, gender, major="", profession="";
        int userType = user.getUserType();
        id = user.getId();
        name = user.getName();
        gender = user.getGender();
        if(user.getUserType() == 1) {
            Teacher teacher = (Teacher) user;
            profession = teacher.getProfession();
        }
        if(user.getUserType() == 2) {
            Student student = (Student) user;
            major = student.getMajor();
        }
        String sql = String.format("INSERT INTO users (userid, pwdHash, id, name, gender, major, profession, userType) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', %d);",
                id, SHA256.get(pwd.toString()), id, name, gender, major, profession, userType);
        return DBUtils.executeUpdate(sql);


    }

    @Override
    public int modify(User user, String pwd) {
        if(this.getById(user.getId()) == null){
            return -1;
        }
        String id, name, gender, major="", profession="";
        int userType = user.getUserType();
        id = user.getId();
        name = user.getName();
        gender = user.getGender();
        if(user.getUserType() == 1) {
            Teacher teacher = (Teacher) user;
            profession = teacher.getProfession();
        }
        if(user.getUserType() == 2) {
            Student student = (Student) user;
            major = student.getMajor();
        }
        String sql;

        if(pwd != null) sql = String.format("UPDATE users SET userid='%s', pwdHash='%s', id='%s', name='%s', gender='%s', major='%s', profession='%s', userType=%d WHERE id='%s';",
                id, SHA256.get(pwd.toString()), id, name, gender, major, profession, userType, id);
        else sql = String.format("UPDATE users SET userid='%s', id='%s', name='%s', gender='%s', major='%s', profession='%s', userType=%d WHERE id='%s';",
                id, id, name, gender, major, profession, userType, id);
        return DBUtils.executeUpdate(sql);
    }

    @Override
    public int remove(String id) {
        if(this.getById(id) == null){
            return -1;
        }
        String sql = String.format("DELETE FROM users WHERE id='%s';", id);
        return DBUtils.executeUpdate(sql);
    }

    @Override
    public boolean login(String userid, String pwdHash) {
        String sql = String.format("SELECT COUNT(*) FROM users WHERE userid='%s' AND pwdHash='%s'", userid, pwdHash);
        ResultSet rs = DBUtils.executeQuery(sql);
        boolean flag = false;
        try {
            rs.next();
            int cnt = rs.getInt(1);
            flag = cnt > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
