package com.xuanfeng.bigdata.utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import java.text.MessageFormat;

public class MockData {

    public static void mockTraffic() throws Exception{
        String domain[] = new String[]{"gifshow.com", "huya.com", "yy.com"};
        String[] time = new String[]{"2020/01/01","2020/01/20","2020/03/02","2020/03/20"};
        String[] traffic = new String[]{"4", "6", "8", "7", "5"};

        Random random = new Random();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                new File("data/inputformat/traffic.csv")))
        );

        for(int i=0; i<=30; i++){
            writer.write(domain[random.nextInt(domain.length)]);
            writer.write(",");
            writer.write(time[random.nextInt(time.length)]);
            writer.write(",");
            writer.write(traffic[random.nextInt(traffic.length)]);
            writer.newLine();
        }

        writer.flush();
        writer.close();
    }

    public static void mockQuestion() throws Exception{

        Random random = new Random();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                new File("data/inputformat/question.csv")))
        );

        for(int i=0; i<=30; i++){
            writer.write("http://ruozedata.com/question/");
            writer.write(String.valueOf(random.nextInt(200)));
            writer.newLine();
        }

        writer.flush();
        writer.close();
    }

    public static void mockCourse() throws Exception{
        String[] courseList = new String[]{"http://ruozedata.com/course/{0}.html", "http://ruozedata.com/course/{0}/{1}.html?a=b&c=d"};
        Random random = new Random();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                new File("data/inputformat/course.csv")))
        );

        for(int i=0; i<=30; i++){
            String format1 = MessageFormat.format(courseList[0], random.nextInt(200), random.nextInt(8));
            writer.write(format1);
            writer.newLine();
            String format2 = MessageFormat.format(courseList[1], random.nextInt(200), random.nextInt(8));
            writer.write(format2);
            writer.newLine();
        }

        writer.flush();
        writer.close();
    }




    public static void main(String[] args) throws Exception{
        //mockTraffic();
        //mockQuestion();
        mockCourse();

    }



}
