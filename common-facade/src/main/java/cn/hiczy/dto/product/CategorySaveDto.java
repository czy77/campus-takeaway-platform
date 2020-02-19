package cn.hiczy.dto.product;


import lombok.Data;

/**
 * @author saco
 */

@Data
public class CategorySaveDto {

    /**category id*/
    private Long id;

    /**category 名称*/
    private String name;

    /**category所属的商铺(restaurant) id"*/
    private Long restaurantId;
}
