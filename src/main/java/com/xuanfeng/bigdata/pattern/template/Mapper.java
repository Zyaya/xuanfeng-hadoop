package com.xuanfeng.bigdata.pattern.template;

public abstract class Mapper {
    /**
     *
     */
    abstract void setUp();
    abstract void cleanUp();
    abstract void map();

    public void run(){
        setUp();
        map();
        cleanUp();
    }
}
