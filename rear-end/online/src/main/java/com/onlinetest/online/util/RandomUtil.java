package com.onlinetest.online.util;

import java.util.*;

/**
 * @author kevinhuang
 * @date 2020-06-25 11:10
 * 随机生成工具类
 */
public class RandomUtil {

    /**
     * 1.从区间[l,r)中随机抽取total个数，返回数的索引列表
     * @param l
     * @param r
     * @param total
     * @return
     */
    public static List<Integer> getRandomNum(int l, int r, int total){
        List<Integer> randomlist = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        while (true){
            if(total == 0) break;
            Integer e = l + (int) (Math.random() * (r - l));
            if(!set.contains(e)) {
                randomlist.add(e);
                set.add(e);
                total--;
            }
        }

        return randomlist;

    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.getRandomNum(0,10,3));
    }
}
