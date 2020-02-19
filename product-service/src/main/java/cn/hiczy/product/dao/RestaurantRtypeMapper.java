package cn.hiczy.product.dao;


import org.apache.ibatis.annotations.Param;

/**
 * @author saco
 */
public interface RestaurantRtypeMapper {

    /**
     * 在RestaurantRtype 中间表添加一条记录
     * @param restaurantId
     * @param rtypeId
     * @return
     */
    int insert(@Param("restaurantId") Long restaurantId,
               @Param("rtypeId") Long rtypeId);

    /**
     * 从RestaurantRtype 中间表删除一条记录
     * @param restaurantId
     * @param rtypeId
     * @return
     */
    int delete(@Param("restaurantId") Long restaurantId,
               @Param("rtypeId") Long rtypeId);

}
