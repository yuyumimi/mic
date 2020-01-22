package cn.com.capinfo.common.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomPrincipal
 * @Description Authentication中获取用户信息
 * @Author yuyu
 * @Date 2019/11/14 16:26
 * @Version 1.0
 **/
@Setter
@Getter
public class CustomPrincipal implements Serializable {
    private String userId;
    private String idcard;
    private String name;
    private String authLevel;
    private String orgCode;
    private Boolean isNewUser;

    private Collection<? extends GrantedAuthority> authorities;
}
