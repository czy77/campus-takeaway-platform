package cn.hiczy.rest.controller.product;



import cn.hiczy.dto.product.PageInfoDto;
import cn.hiczy.dto.product.RestaurantAddDto;
import cn.hiczy.dto.product.RestaurantUpdateDto;
import cn.hiczy.entity.product.Restaurant;
import cn.hiczy.service.product.IRestaurantService;
import cn.hiczy.vo.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author saco
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Reference
    private IRestaurantService restaurantService;


    @GetMapping
    @ApiOperation("根据rtypeId分页查询商铺列表")
    public CommonResult<List<Restaurant>> restaurants(@RequestParam(value = "rtypeId")@ApiParam("商铺类型id") Long rtypeId,
                                                      @ApiParam("分页传输对象") PageInfoDto pageInfoDto) {
        List<Restaurant> restaurants = restaurantService.selectRestaurantsByRtypeId(rtypeId, pageInfoDto);
        return CommonResult.success(restaurants);
    }



    @GetMapping("{id:\\d+}")
    @ApiOperation("根据id获取商铺")
    public CommonResult<Restaurant> restaurant(@PathVariable("id")
                                                   @ApiParam("商铺id") Long id) {
        Restaurant restaurant = restaurantService.getById(id);

        return CommonResult.success(restaurant);
    }

    @PostMapping
    @ApiOperation("添加一个商铺")
    public CommonResult<Boolean> addRestaurant(RestaurantAddDto restaurantAddDto) {
        Boolean b = restaurantService.addRestaurant(restaurantAddDto);
        return CommonResult.success(b);
    }

    @PutMapping
    @ApiOperation("修改商铺信息")
    public CommonResult<Boolean> updateRestaurant(RestaurantUpdateDto restaurantUpdateDto) {
        Boolean b = restaurantService.updateRestaurant(restaurantUpdateDto);
        return CommonResult.success(b);
    }

}

