package cn.edu.hit.sms.utils;

import java.sql.*;

public class DBUtils {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.101.32.121:3306/education_system";

    static final String USER = "eduSys";
    static final String PASS = "123456";

    private static Connection con;
    private static Statement stmt;
    static {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql) {
        try {
            System.out.printf("%s\n", sql);
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int executeUpdate(String sql) {
        try {
            System.out.printf("%s\n", sql);
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static void close() {
        try {
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
