package com.xuanfeng.bigdata.pattern.template;

public class XuanFengMapper extends Mapper{


    @Override
    void setUp() {
        System.out.println("-----XuanFengMapper setUp----");
    }

    @Override
    void cleanUp() {
        System.out.println("-----XuanFengMapper cleanUp----");
    }

    @Override
    void map() {
        System.out.println("-----XuanFengMapper map----");
    }
}
