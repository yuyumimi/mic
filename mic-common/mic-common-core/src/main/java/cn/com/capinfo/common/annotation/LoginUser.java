package cn.com.capinfo.common.annotation;

import java.lang.annotation.*;

/**
 *@ClassName LoginUser
 *@Description 请求的方法参数LoginUserInfo上添加该注解，则注入当前登录人信息
 *  例1：public void test(@LoginUser LoginUserInfo user) //只有username
 *  例2：public void test(@LoginUser(isFull = true) LoginUserInfo user)
 *  //能获取SysUser对象的所有信息
 *@Author yuyu
 *@Date 2019/11/26 8:44
 *@Version 1.0
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
    /**
     * 是否查询SysUser对象所有信息，true则通过rpc接口查询
     */
    boolean isFull() default false;
}
