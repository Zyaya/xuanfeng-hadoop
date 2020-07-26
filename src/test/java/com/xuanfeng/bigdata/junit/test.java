package com.xuanfeng.bigdata.junit;

import org.junit.Test;

public class test {
    @Test
    public void test(){
        String[] fileContent = new String[]{"http://ruozedata.com/course/{0}.html", "http://ruozedata.com/course/{0}/{1}.html?a=b&c=d   "};
        String logFormat = "http://ruozedata.com/question/{0}";
        for (String file : fileContent){
            System.out.println();
        }
        long fileSize = 70 * 1024 * 1024;
        long rowNum = fileSize / logFormat.length();
    }

    @Test
    public void test2(){

    }
}
