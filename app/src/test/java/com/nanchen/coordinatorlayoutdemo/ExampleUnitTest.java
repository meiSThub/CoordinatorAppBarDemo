package com.nanchen.coordinatorlayoutdemo;

import org.junit.Test;

import android.util.Log;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static final String TAG = "ExampleUnitTest";

    @Test
    public void addition_isCorrect() throws Exception {
        // assertEquals(4, 2 + 2);
        boolean isCorrect = false;
        isCorrect |= true;
        System.out.println("addition_isCorrect:isCorrect= " + isCorrect);
        isCorrect |= false;
        System.out.println("addition_isCorrect:isCorrect= " + isCorrect);

        StringBuilder builder = new StringBuilder();
        builder.delete(0, builder.length());
        System.out.println("没有异常 ");

    }
}