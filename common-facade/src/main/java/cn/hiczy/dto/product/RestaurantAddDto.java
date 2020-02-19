package cn.hiczy.dto.product;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author saco
 */
@Data
@Accessors(chain = true)
public class RestaurantAddDto {

    /**商铺名称*/
    @NotBlank
    private String name;

    /**商铺图片*/
    @NotBlank
    private String picture;

    /**商铺介绍*/
    private String description;

    /**商铺联系电话*/
    @NotBlank
    private String tel;

    @NotNull
    /**商铺类型id*/
    private List<Long> rtypeIdList;
}
