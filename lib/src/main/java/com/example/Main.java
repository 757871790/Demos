package com.example;

import java.util.Random;

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2016/11/11.
 */

public class Main {
    public interface Generator<T> {
        public T next();
    }

    public static class FruitGenerator implements Generator<String> {


        private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

        @Override
        public String next() {
            Random rand = new Random();
            return fruits[rand.nextInt(3)];
        }
    }

    public static void main(String[] args) {
        Generator<String> generator = new FruitGenerator();
        System.out.println(generator.next());
        System.out.println(generator.next());
        System.out.println(generator.next());
        System.out.println(generator.next());


        System.out.println(isNummber(""));


        System.out.println(~555555555);

    }

    private static int isNummber(String numberString) {
        int k = 1;
        try {
            k = 2;
            return k;
        } finally {
            k = 3;
            return k;
        }
    }
}
