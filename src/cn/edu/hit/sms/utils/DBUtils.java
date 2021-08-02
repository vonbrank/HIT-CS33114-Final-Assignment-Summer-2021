package cn.edu.hit.sms.utils;

import java.sql.*;

public class DBUtils {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.101.32.121:3306/education_system";

    static final String USER = "eduSys";
    static final String PASS = "123456";

    private static Connection con;
    private static Statement stmt;

    public static ResultSet executeQuery(String sql) {
        return null;
    }

    public static int executeUpdate(String sql) {
        return 0;
    }

    public static void close() {

    }

}
