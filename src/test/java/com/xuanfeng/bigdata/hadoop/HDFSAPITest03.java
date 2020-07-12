package com.xuanfeng.bigdata.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;


public class HDFSAPITest03 {


    /**
     *
     * 使用流的方式，不采用API
     */

    FileSystem fileSystem;

    @Before
    public void setUp() throws Exception{

        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname","true");
        configuration.set("dfs.replication","1");
        URI uri = new URI("hdfs://ruozedata001:8020");
        fileSystem = FileSystem.get(uri, configuration, "hadoop");
    }

    @Test
    public void copyFromLocalFile() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(new File("/Users/zhangyalin/Downloads/Centos-7.repo"));
        BufferedInputStream in = new BufferedInputStream(fileInputStream);

        Path path = new Path("/hdfsapi/xuanfeng/io.txt/");
        FSDataOutputStream out = fileSystem.create(path);
        //  站在本地的角度看，从本地拷个东西到服务器上，是输出流

        //  in-->out
        IOUtils.copyBytes(in, out, 2048);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }

    @Test
    public void copyToLocalFile() throws Exception{

        //  站在服务器角度看，是将文件输出到本地，所以要开个流将内容灌入
        Path path = new Path("/hdfsapi/xuanfeng/io.txt");
        FSDataInputStream in = fileSystem.open(path);


        //  站在本地角度看，是将文件输入到本地，所以要开个流让"in"里到内容输出去
        FileOutputStream fileOutputStream = new FileOutputStream(new File("out/io-out.txt"));
        BufferedOutputStream out = new BufferedOutputStream(fileOutputStream);


        IOUtils.copyBytes(in, out, 2048);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);


    }


    @Test
    public void download01() throws Exception {
        FSDataInputStream in = fileSystem.open(new Path("/hdfsapi/xuanfeng/hbase-1.2.0-cdh5.16.2.tar.gz"));
        FileOutputStream out = new FileOutputStream(new File("out/hbase.tgz.part0"));
        // 0 -128M

        IOUtils.copyBytes(in, out,128*1024*1024L, false);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }

    @Test
    public void download02() throws Exception{
        FSDataInputStream in = fileSystem.open(new Path("/hdfsapi/xuanfeng/hbase-1.2.0-cdh5.16.2.tar.gz"));
        FileOutputStream out = new FileOutputStream(new File("out/hbase.tgz.part1"));

        //  128M~256M
        in.seek(1024*1024*128);
        IOUtils.copyBytes(in, out, fileSystem.getConf());
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }
}
