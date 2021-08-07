package cn.edu.hit.sms.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DBUtils {
    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL;

    static String USER;
    static String PASS;

    private static Connection con;
    private static Statement stmt;

    static {
        Properties properties = new Properties();
        BufferedReader bufferedReader = null;

        try {
            Path relative = Paths.get("../conf/sms.properties");
            Path absolute = relative.toAbsolutePath();
            bufferedReader = new BufferedReader(new FileReader(String.valueOf(absolute.toRealPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String HostAddress = properties.getProperty("HostAddress");
        String Database = properties.getProperty("Database");
        USER = properties.getProperty("Username");
        PASS = properties.getProperty("Password");
        DB_URL = String.format("jdbc:mysql://%s/%s", HostAddress, Database);

        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(JDBC_DRIVER);
        System.out.println(DB_URL);
        System.out.println(USER);
        System.out.println(PASS);
    }

    public static ResultSet executeQuery(String sql) {
        try {
//            System.out.printf("%s\n", sql);
            Logger.getGlobal().info(sql);
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int executeUpdate(String sql) {
        try {
//            System.out.printf("%s\n", sql);
            Logger.getGlobal().info(sql);
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
