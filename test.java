package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class test {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/kadaidb?serverTimezone=Asia/Tokyo",
                "root",
                "root1234"
            );

            System.out.println("接続成功");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}