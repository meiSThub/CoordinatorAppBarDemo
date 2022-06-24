package com.nanchen.coordinatorlayoutdemo;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author mxb
 * @date 2019/5/10
 * @desc
 * @desired
 */
public class WithdrawUtilTest {

    @Test
    public void checkIdCard() throws ParseException {
        // WithdrawUtil.checkIdCard(1L, "3172054104890002");
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyy");
        //
        // Date d = simpleDateFormat.parse("10489");

        String key = "null";

        switch (key) {
            case "1":
                System.out.println("key1=");
                break;
            case "2":
                System.out.println("key2=");
                break;
            default:
                System.out.println("default=");
                break;
        }
    }

}