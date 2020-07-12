package com.xuanfeng.bigdata.homework.homework0704;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

public class Rename {

    FileSystem fileSystem;

    @Before
    public void setUp() throws Exception{
        Configuration conf = new Configuration();
        conf.set("dfs.client.use.datanode.hostname","true");
        conf.set("dfs.replication","1");
        URI uri = new URI("hdfs://ruozedata001:8020");
        fileSystem = FileSystem.get(uri, conf, "hadoop");
    }


    public void rename(String day) throws Exception{
        Path path = new Path("/hdfsapi/xuanfeng/" + day);
        FileStatus[] statuses = fileSystem.listStatus(path);

        int index = 0;

        for(FileStatus status : statuses){

            Path src = status.getPath();
            //System.out.println(src);

            String p_str = src.toString();
            int i = p_str.indexOf(".");

            p_str = p_str.substring(i);
            //System.out.println(p_str);


            Path dst = new Path("/hdfsapi/xuanfeng/" + day + "-" + Integer.toString(index) + p_str);
            fileSystem.rename(src, dst);

            index ++;
        }


    }

    @Test
    public void renameTest() throws Exception{
        rename("20211102");
    }

    @After
    public void tearDown() throws Exception{
        if(null != fileSystem){
            fileSystem.close();
        }
    }

}
