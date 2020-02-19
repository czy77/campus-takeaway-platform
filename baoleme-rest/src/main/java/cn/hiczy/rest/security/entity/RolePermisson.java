package cn.hiczy.rest.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author saco
 */
@Data
public class RolePermisson {
    @TableId
    private Long id;
    private Long roleId;
    private Long permissionId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @TableLogic
    private Boolean deleteStatus;
}
