package cn.hiczy.product.service;


import cn.hiczy.dto.product.PageInfoDto;
import cn.hiczy.dto.product.RestaurantAddDto;
import cn.hiczy.dto.product.RestaurantUpdateDto;
import cn.hiczy.entity.product.Restaurant;
import cn.hiczy.product.dao.RestaurantMapper;
import cn.hiczy.product.dao.RestaurantRtypeMapper;
import cn.hiczy.service.product.IRestaurantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author saco
 * @since 2020-01-14
 */
@Service
public class RestaurantServiceImpl extends ServiceImpl<RestaurantMapper, Restaurant> implements IRestaurantService {
    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private RestaurantRtypeMapper restaurantRtypeMapper;

    @Override
    public List<Restaurant> selectRestaurantsByRtypeId(Long rtypeId, PageInfoDto pageInfoDto) {
        Integer limit = pageInfoDto.getCount();
        Integer offset = (pageInfoDto.getPageNumber() - 1) * limit;
        List<Restaurant> restaurants = restaurantMapper
                .selectRestaurantsByRtypeId(rtypeId, limit, offset);
        return restaurants;
    }

    
    /**
     * 添加一个商铺,并在restaurant rtype 中间表中插入一条记录
     * @param restaurantAddDto
     * @return
     */
    @Override
    @Transactional
    public Boolean addRestaurant(RestaurantAddDto restaurantAddDto) {
        //添加商铺
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantAddDto, restaurant);
        restaurantMapper.insert(restaurant);
        
        //在中间表中添加商铺和商铺类型的记录
        Long restaurantId = restaurant.getId(); 
        restaurantAddDto.getRtypeIdList().forEach(rtypeId -> restaurantRtypeMapper.insert(restaurantId, rtypeId));
        return true;
    }

    @Override
    public Boolean updateRestaurant(RestaurantUpdateDto restaurantUpdateDto) {
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantUpdateDto, restaurant);
        restaurantMapper.updateById(restaurant);
        return true;
    }


}
