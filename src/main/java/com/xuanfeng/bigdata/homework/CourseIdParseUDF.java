package com.xuanfeng.bigdata.homework;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(name = "CourseIdParse",
        value = "_FUNC_(http://ruozedata.com/course/117/3.html?a=b&c=d) - Returns str : 117_3",
        extended = "Example:\n"
                + "  > SELECT _FUNC_('http://ruozedata.com/course/117/3.html?a=b&c=d') FROM src LIMIT 1;\n" + "  '117_3'")
public class CourseIdParseUDF extends UDF {

    public String evaluate(String url) throws Exception{

        int index = url.indexOf(".html");
        String sub = url.substring(index);
       // System.out.println(i);
        return url.replaceAll("\\?","")
                .replaceAll("http://ruozedata.com/course/","")
                    .replaceAll(sub,"")
                        .replaceAll("/","_");

    }


}
