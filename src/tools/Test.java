package tools;

import cn.edu.hit.sms.dao.CourseDao;
import cn.edu.hit.sms.dao.UserDao;
import cn.edu.hit.sms.dao.impl.CourseDaoImpl;
import cn.edu.hit.sms.dao.impl.UserDaoImpl;
import cn.edu.hit.sms.entity.course.Course;
import cn.edu.hit.sms.entity.course.Score;
import cn.edu.hit.sms.entity.user.Staff;
import cn.edu.hit.sms.entity.user.Student;
import cn.edu.hit.sms.entity.user.Teacher;
import cn.edu.hit.sms.entity.user.User;
import cn.edu.hit.sms.utils.DBUtils;

import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Logger;

import tools.EasyFileIO;
public class Test {
    public static void main(String[] args) {

//        courseDaoTest();
        Logger.getGlobal().info("Test");
    }

    static void userDaoTest() {
        UserDao userDao = new UserDaoImpl();

        List<Student> userList = userDao.getAllStudents();
        for(User user : userList) {
//            System.out.println(user.toString());
        }
//        System.out.println(userDao.getById("S108"));
//        System.out.println(userDao.getByName("Gale Holt"));
//        Student student = new Student("S109", "XiaoMing", "Male", "Computer Science");
//        System.out.println(userDao.add(student, "123456"));
//        System.out.println(userDao.modify(student, "234567"));
//        System.out.println(userDao.remove("S109"));
    }

    static void courseDaoTest() {
        CourseDao courseDao = new CourseDaoImpl();
        UserDao userDao = new UserDaoImpl();
//        System.out.println(courseDao.getNumberOfStudentByCid("C004"));
//        List<Course> courseList = courseDao.getCourseByTname("Shirley Sam");
//        for (Course course : courseList) {
//            System.out.println(course.toString() + '\n');
//        }
//        System.out.println(courseDao.getCourseByCid("C003").toString() + '\n');
//        System.out.println(courseDao.getCourseByCname("C003"));
//        System.out.println(courseDao.getCourseByCname("Biochemistry B").toString() + '\n');
//        Course course1 = new Course("C23", "College Chinese", (Teacher) userDao.getById("T007"), 0);
//        System.out.println(courseDao.addCourse(course1));
//        System.out.println(courseDao.modifyCourse(course1));
//        System.out.println(courseDao.removeCourse("C23"));
        List<Course> courseList = courseDao.getCourseByTid("T007");
        for (Course course : courseList) {
            System.out.println(course.toString() + '\n');
        }

        System.out.println(courseDao.getNumberOfCourseByTid("T006"));
    }

    static void scoreDaotest() {
        CourseDao courseDao = new CourseDaoImpl();
        UserDao userDao = new UserDaoImpl();
        SecureRandom rand = new SecureRandom();
        List<Score> scoreList = courseDao.getAllScores();
        for(Score score : scoreList) {
            System.out.println(score.toString() + "\n");
        }
        Student student = (Student) userDao.getById("S108");
        Course course = courseDao.getCourseByCid("C001");
        Score score = new Score(student, course, 88);
//        courseDao.addScore(score);
//        courseDao.modifyScore(score);
        System.out.println(courseDao.removeScore(score));
    }
}
