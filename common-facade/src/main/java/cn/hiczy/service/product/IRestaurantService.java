package cn.hiczy.service.product;



import cn.hiczy.dto.product.PageInfoDto;
import cn.hiczy.dto.product.RestaurantAddDto;
import cn.hiczy.dto.product.RestaurantUpdateDto;
import cn.hiczy.entity.product.Restaurant;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author saco
 * @since 2020-01-14
 */
public interface IRestaurantService extends IService<Restaurant> {

    /**
     *  根据 商铺类型分页返回商铺列表
     * @param rtypeId
     * @param pageInfoDto
     * @return
     */
    List<Restaurant> selectRestaurantsByRtypeId(Long rtypeId, PageInfoDto pageInfoDto);

    /**
     * 添加一个商铺
     * @param restaurantAddDto
     * @return
     */
    Boolean addRestaurant(RestaurantAddDto restaurantAddDto);

    /**
     * 更新商铺信息
     * @param restaurantUpdateDto
     * @return
     */
    Boolean updateRestaurant(RestaurantUpdateDto restaurantUpdateDto);



}
