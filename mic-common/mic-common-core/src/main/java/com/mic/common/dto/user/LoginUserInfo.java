package com.mic.common.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName LoginUser
 * @Description 查询出的用户信息,安全起见，不要直接发送前端
 * @Author yuyu
 * @Date 2019/11/26 8:48
 * @Version 1.0
 **/
@Setter
@Getter
public class LoginUserInfo implements Serializable {
    private String userId;
    private String username;
    private String idcard;
    private String authLevel;
    private String orgCode;
}
