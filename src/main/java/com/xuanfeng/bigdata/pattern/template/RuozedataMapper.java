package com.xuanfeng.bigdata.pattern.template;

public class RuozedataMapper extends Mapper{


    @Override
    void setUp() {
        System.out.println("-----RuozedataMapper setUp----");
    }

    @Override
    void cleanUp() {
        System.out.println("-----RuozedataMapper cleanUp----");
    }

    @Override
    void map() {
        System.out.println("-----RuozedataMapper map----");
    }
}
