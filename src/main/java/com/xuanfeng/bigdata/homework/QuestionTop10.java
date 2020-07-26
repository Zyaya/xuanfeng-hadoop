package com.xuanfeng.bigdata.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * 统计最热门的问答对Top10
 * http://ruozedata.com/question/100
 * ...
 * ..
 */

public class QuestionTop10 {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws Exception {
        Class.forName(driverName);
        Connection con = DriverManager.getConnection("jdbc:hive2://xuanfeng001:10000/default", "hadoop", "");
        Statement stmt = con.createStatement();
        String sql = "select substr(url,31) page,count(1) as pv\n" +
                "from question\n" +
                "group by substr(url,31)\n" +
                "order by pv desc\n" +
                "limit 10 ";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString("page")+ "-->" + res.getInt("pv"));
        }
    }
}
