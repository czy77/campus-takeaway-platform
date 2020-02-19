package cn.hiczy.service.product;


import cn.hiczy.dto.product.FoodSavaDto;
import cn.hiczy.dto.product.PageInfoDto;
import cn.hiczy.entity.product.Food;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  Food服务类
 * </p>
 *
 * @author saco
 * @since 2020-01-14
 */
public interface IFoodService extends IService<Food> {
    /**
     * 根据商铺分页返回 foodList
     *
     * @param rid
     * @param pageInfoDto
     * @return
     */
    List<Food> selectFoodListByRid(Long rid, PageInfoDto pageInfoDto);

    /**
     * 根据 标签返回 food List
     * @param cid
     * @return
     */
    List<Food> selectFoodListByCid(Long cid);


    /**
     * 更新food信息
     * @param foodSaveDto
     * @return
     */
    Boolean saveFoodInfo(FoodSavaDto foodSaveDto);



}
