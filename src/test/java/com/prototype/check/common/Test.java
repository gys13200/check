package com.prototype.check.common;

import org.prototype.check.common.ParameterExpression;

import java.util.Map;

/**
 * Created by root on 2017/4/1.
 */
public class Test {


    @org.junit.Test
    public void test(){
        Map<String, String> map = new ParameterExpression("valued, type='djf,ie', initOption='请选择', initValue='1'");
        System.out.println(map);
    }

}
