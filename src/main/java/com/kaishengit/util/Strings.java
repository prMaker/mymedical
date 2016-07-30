package com.kaishengit.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.io.UnsupportedEncodingException;

public class Strings {

    public static String toUTF8(String str) {
        if(StringUtils.isNotEmpty(str)) {
            try {
                return new String(str.getBytes("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("字符串转码异常");
            }
        }
//        TODO 不要ruturn出“” 这样哪里错了也不知道
        return null;
    }

    public static String toPinyin(String str){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            return PinyinHelper.toHanYuPinyinString(str,format,"",true);
        } catch (BadHanyuPinyinOutputFormatCombination ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 根据身份证号产生年龄
     * @param idcard
     * @return
     */
    public static Integer getAge(String idcard) {
        String birth = idcard.substring(6,15);
        System.out.println(birth);

        DateTime dateTime = new DateTime(Integer.valueOf(birth.substring(0,4)).intValue(),Integer.valueOf(birth.substring(4,6)).intValue(),Integer.valueOf(birth.substring(6)).intValue(),0,0);
        DateTime dateTimeNow = DateTime.now();
        Integer year = dateTimeNow.getYear() - dateTime.getYear();
        Integer month = dateTimeNow.getMonthOfYear() - dateTime.getMonthOfYear();
        Integer day = dateTimeNow.getDayOfMonth() - dateTime.getDayOfMonth();
        if(month < 0){
            return year-1;
        }else if(month > 0){
            return year;
        }else{
            if(day < 0){
                return year-1;
            }else{
                return year;
            }
        }
    }
}
