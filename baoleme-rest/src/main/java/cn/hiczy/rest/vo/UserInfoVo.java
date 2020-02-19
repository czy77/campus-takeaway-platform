package cn.hiczy.rest.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author saco
 */
@Data
public class UserInfoVo {
    private Long id;
    /**用户的手机号码*/
    private String phoneNumber;
    private String username;
    private String nickname;
    /**用户所拥有的权限*/
    private List<GrantedAuthority> authorities;
}
