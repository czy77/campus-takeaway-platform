package cn.hiczy.dto.product;


import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author saco
 */

@Data
public class FoodSavaDto {

    /**食物 id.不传入id表示新增食物,传入id表示修改食物*/
    private Long id;

    @NotBlank
    /**食物名称"*/
    private String name;

    /**食物具体描述"*/
    private String description;

    /**食物图片"*/
    private String picture;

    @NotNull
    @DecimalMin("0")
    /**食物价格"*/
    private BigDecimal price;

    /**食物所属的标签(category)id*/
    @NotNull
    private Long cId;

    @NotNull
    /**食物所属的商铺(restaurant)id */
    private Long rId;

}
