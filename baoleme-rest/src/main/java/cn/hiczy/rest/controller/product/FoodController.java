package cn.hiczy.rest.controller.product;


import cn.hiczy.dto.product.FoodSavaDto;
import cn.hiczy.dto.product.PageInfoDto;
import cn.hiczy.entity.product.Food;

import cn.hiczy.service.product.IFoodService;
import cn.hiczy.vo.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/food")
public class FoodController {

    @Reference
    IFoodService foodService;

    @ApiOperation("根据ID获取一条food记录")
    @GetMapping("{id:\\d+}")
    public CommonResult<Food> getFoodById(@PathVariable("id")
                                              @ApiParam("食物id") Long id) {
        Food food = foodService.getById(id);
        return CommonResult.success(food);
    }


    @GetMapping
    @ApiOperation("根据商铺分页查询食物")
    public CommonResult<List<Food> > allFood(@RequestParam("rid")
                                  @ApiParam("商铺id") Long rid, @ApiParam("分页信息") PageInfoDto pageInfoDto) {
        List<Food> foods = foodService.selectFoodListByRid(rid, pageInfoDto);
        return CommonResult.success(foods);
    }


    @DeleteMapping("{fId:\\d+}")
    @ApiOperation("根据食物id删除食物")
    public CommonResult<Boolean> deleteFood(@PathVariable("fId")
                                       @ApiParam("食物ID") Long fId) {
        Boolean b = foodService.removeById(fId);
        return CommonResult.success(b);
    }


    @PostMapping
    @ApiOperation("新增或更新食物信息")
    public CommonResult<Boolean> saveFood(@RequestBody
                                                @ApiParam("食物新信息")
                                                  FoodSavaDto foodSaveDto) {
        Boolean b = foodService.saveFoodInfo(foodSaveDto);
        return CommonResult.success(b);
    }



}

