package com.xuanfeng.bigdata.homework.homework0704;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class MergeFile {

    public abstract class AbstractMergeFile {

        /**
         * 模版模式练习
         */
        abstract void setUp() throws Exception;

        abstract void mergeFile() throws Exception;

        abstract void tearDown() throws IOException;

        public void run() throws Exception {
            setUp();
            mergeFile();
            tearDown();
        }
    }

    public class Merge extends AbstractMergeFile {

        FileSystem fileSystem;

        @Override
        void setUp() throws Exception {

            Configuration configuration = new Configuration();
            configuration.set("dfs.client.use.datanode.hostname", "true");
            configuration.set("dfs.replication", "1");
            URI uri = new URI("hdfs://xuanfeng001:8020");
            fileSystem = FileSystem.get(uri, configuration, "hadoop");

            System.out.println("-------setUp------");
        }

        @Override
        void mergeFile() throws Exception {

            FileInputStream fileInputStream = new FileInputStream(new File("out/hbase.tgz.part0_bak"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("out/hbase.tgz"));

            IOUtils.copyBytes(fileInputStream, fileOutputStream, 4096);

            fileInputStream = new FileInputStream(new File("out/hbase.tgz.part1_bak"));

            IOUtils.copyBytes(fileInputStream, fileOutputStream, 4096);

            IOUtils.closeStream(fileInputStream);
            IOUtils.closeStream(fileOutputStream);

        }

        @Override
        void tearDown() throws IOException {
            if (null != fileSystem) {
                fileSystem.close();
            }
            System.out.println("------tearDown-----");
        }

    }

    @Test
    public void testMergeFile() throws Exception {
        Merge merge = new Merge();
        try {
            merge.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

