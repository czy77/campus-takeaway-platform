package cn.hiczy.dto.product;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author saco
 */
@Data

public class RestaurantUpdateDto {
    /**商铺id*/
    @NotNull
    private Long rid;

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
}
