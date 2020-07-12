package com.xuanfeng.bigdata.pattern.template;

public class Client {

    public static void main(String[] args) {
        Mapper xuanFengMapper = new XuanFengMapper();
        xuanFengMapper.run();

        System.out.println("---------");

        Mapper ruozedataMapper = new RuozedataMapper();
        ruozedataMapper.run();

    }
}
