package com.example.library;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Rollin on 2015/10/28.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestUtilsTest {

    @Test
    public void test(){
        String expected = "hello world";
        String result = TestUtils.hello();
        Assert.assertEquals(" hello return invalid",expected, result);
    }
}
