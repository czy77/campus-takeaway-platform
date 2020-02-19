package cn.hiczy.rest.controller.product;



import cn.hiczy.dto.product.CategorySaveDto;
import cn.hiczy.entity.product.Category;
import cn.hiczy.service.product.ICategoryService;
import cn.hiczy.vo.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/category")
public class CategoryController {


    @Reference
    private ICategoryService categoryService;

    @GetMapping
    @ApiOperation("根据商铺id返回该商铺的所有食物标签")
    public CommonResult<List<Category> > categoryList(@RequestParam("rid")
                                                          @ApiParam("商铺id") Long rid) {
        List<Category> categories = categoryService.selectCategoriesByRid(rid);
        return CommonResult.success(categories);
    }


    @PostMapping
    @ApiOperation("更新或新增标签信息")
    public CommonResult<Boolean> saveCategory(@ApiParam("标签传输对象") CategorySaveDto categorySaveDto) {
        Boolean b = categoryService.saveCategoryInfo(categorySaveDto);
        return CommonResult.success(b);
    }


    @DeleteMapping("{id:\\d+}")
    @ApiOperation("根据id删除一个标签")
    public CommonResult<Boolean> deleteCategoryById(@PathVariable("id")
                                                        @ApiParam("标签(category)id") Long id) {
        Boolean b = categoryService.removeById(id);
        return CommonResult.success(b);
    }



}

