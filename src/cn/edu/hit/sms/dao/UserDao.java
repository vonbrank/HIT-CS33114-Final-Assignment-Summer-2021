package cn.edu.hit.sms.dao;

import cn.edu.hit.sms.entity.user.*;

import java.util.List;

public interface UserDao {
    public List<User> getAll();
    public List<Staff> getAllStaffs();
    public List<Teacher> getAllTeachers();
    public List<Student> getAllStudents();
    public User getById(String id);

    public User getByName(String name);

    public int add(User user, String pwdHash);

    public int modify(User user, String pwd);

    public int remove(String id);

}
