package com.xuanfeng.bigdata.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;

/**
 * HDFS API 测试用例
 *
 *
 * 编程：入口点
 */
public class HDFSAPITest02 {

    FileSystem fileSystem;

    @Before
    public void setUp() throws Exception{
        Configuration conf = new Configuration();
        conf.set("dfs.client.use.datanode.hostname","true");
        conf.set("dfs.replication","3");
        URI uri = new URI("hdfs://ruozedata001:8020");
        fileSystem = FileSystem.get(uri, conf, "hadoop");
    }

    @After
    public void tearDown() throws Exception{
        if(null != fileSystem){
            fileSystem.close();
        }
    }


    @Test
    public void mkdir() throws Exception{
        Path path = new Path("/hdfsapi/xuanfeng");
        fileSystem.mkdirs(path);
    }

    @Test
    public void copyFromLocalFile() throws Exception{
        Path src = new Path("/Users/zhangyalin/Downloads/Centos-7.repo");
        Path dst = new Path("/hdfsapi/xuanfeng");
        fileSystem.copyFromLocalFile(src, dst);
    }

    @Test
    public void delete() throws Exception{
        boolean result = fileSystem.delete(new Path("/hdfsapi/xuanfeng/Centos-7.repo"), false);
    }

    @Test
    public void copyToLocalFile() throws Exception{
        Path src = new Path("/hdfsapi/xuanfeng/Centos-7.repo");
        Path dst = new Path("out/Centos-7.repo");
        fileSystem.copyToLocalFile(src, dst);

    }

    @Test
    public void testReplication() throws Exception{
        System.out.println(fileSystem.getConf().get("dfs.replication"));
    }

    @Test
    public void rename() throws Exception{
        Path src = new Path("/hdfsapi/xuanfeng/Centos-7.repo");
        Path dst = new Path("/hdfsapi/xuanfeng/Centos-7-new.repo");
        boolean result = fileSystem.rename(src, dst);
    }

    @Test
    public void listFile() throws Exception{
        Path path = new Path("/hdfsapi/xuanfeng/Centos-7.repo");
        Path path2 = new Path("/hdfsapi/xuanfeng/");
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(path2, true);
        while(files.hasNext()){
            LocatedFileStatus fileStatus = files.next();
            String isDir = fileStatus.isDirectory() ? "文件夹":"文件";
            Path path1 = fileStatus.getPath();

            System.out.println(isDir);
            System.out.println(path1);
            //  为什么是数组，因为一个文件可能被拆成多个块
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println(blockLocations);
            for(BlockLocation blockLocation : blockLocations){
                //  为什么是数组，因为多副本，被放在不同机器上
                String[] hosts = blockLocation.getHosts();
                long length = blockLocation.getLength();
                System.out.println(length);
                for(String host : hosts){
                    System.out.println(host);
                }
            }
        }
    }


}
