package com.kaishengit.Util;

import com.kaishengit.util.Strings;
import org.junit.Test;

/**
 * Created by Administrator on 2016/7/30.
 */
public class UtilTest {

    @Test
    public void testOne(){
        System.out.println(Strings.getAge("410881199309013539"));
    }

    @Test
    public void testPinyin(){
        System.out.println(Strings.toPinyin("中国"));
    }

}
