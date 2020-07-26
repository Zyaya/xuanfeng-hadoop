package com.xuanfeng.bigdata.hive;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JDBCApp {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws Exception {
        Class.forName(driverName);
        Connection con = DriverManager.getConnection("jdbc:hive2://xuanfeng001:10000/default", "hadoop", "zihaoahaha");
        Statement stmt = con.createStatement();
        String tableName = "question";
        String sql = "select * from " + tableName;
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }
}

