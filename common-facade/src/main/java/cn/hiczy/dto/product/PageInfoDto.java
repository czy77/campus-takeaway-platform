package cn.hiczy.dto.product;

import lombok.Data;

/**
 * @author saco
 */
@Data
public class PageInfoDto {
    /**每一页的数量*/
    private Integer count = 20;


    /**页码*/
    private Integer pageNumber = 1;

}
