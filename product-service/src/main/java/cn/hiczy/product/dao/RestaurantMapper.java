package cn.hiczy.product.dao;

import cn.hiczy.entity.product.Restaurant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author saco
 * @since 2020-01-14
 */
public interface RestaurantMapper extends BaseMapper<Restaurant> {

    /**
     * 根据 商铺类型分页返回商铺列表
     * @param rtypeId
     * @param limit
     * @param offset
     * @return
     */
    List<Restaurant> selectRestaurantsByRtypeId(@Param("rtypeId") Long rtypeId,
                                                @Param("limit") Integer limit,
                                                @Param("offset") Integer offset);




}
