package cn.com.capinfo.common.annotation;

import java.lang.annotation.*;

/**
 *@ClassName LoginClient
 *@Description 请求的方法参数上添加该注解，则注入当前登录账号的应用id
 *  例：public void test(@LoginClient String clientId) //注入webApp
 *@Author yuyu
 *@Date 2019/11/26 8:43
 *@Version 1.0
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginClient {
}
