package cn.com.capinfo.common.util;

import cn.com.capinfo.common.dto.user.CustomPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @ClassName SecurityContextUtil
 * @Description 获取上下文中的用户信息
 * @Author yuyu
 * @Date 2019/11/21 14:39
 * @Version 1.0
 **/
public class SecurityContextUtil {

    public static CustomPrincipal getUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Object obj = authentication.getPrincipal();
        String principal=null;
        if(obj instanceof String){
            principal = (String)obj;

        }else if(obj instanceof UserDetails){
            UserDetails userDetails = (UserDetails)obj;
            principal= userDetails.getUsername();
        }
        if (StringUtils.isBlank(principal)) {
            throw new UsernameNotFoundException("获取用户principal异常");
        }
        CustomPrincipal customPrincipal=
                FastJsonUtils.convertJsonToObject(principal,
                        CustomPrincipal.class);
        customPrincipal.setAuthorities(authentication.getAuthorities());
        return customPrincipal;
    }
}
