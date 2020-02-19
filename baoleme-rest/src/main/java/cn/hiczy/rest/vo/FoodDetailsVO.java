package cn.hiczy.rest.vo;



import cn.hiczy.entity.product.Category;
import cn.hiczy.entity.product.Restaurant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author saco
 */
@Data
public class FoodDetailsVO {

    @ApiModelProperty("食物ID")
    private Long id;

    @ApiModelProperty("食物名称")
    private String name;

    @ApiModelProperty("食物描述")
    private String description;

    @ApiModelProperty("食物图片url")
    private String picture;

    @ApiModelProperty("食物价格")
    private BigDecimal price;

    @ApiModelProperty("食物标签信息")
    private Category category;

    @ApiModelProperty("食物所属商铺信息")
    private Restaurant restaurant;
}
