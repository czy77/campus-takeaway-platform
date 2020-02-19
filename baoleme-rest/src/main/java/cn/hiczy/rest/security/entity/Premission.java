package cn.hiczy.rest.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author saco
 */
@Data
public class Premission {
    @TableId
    private Long id;
    private String permissionCode;
    private String permissionName;
}
