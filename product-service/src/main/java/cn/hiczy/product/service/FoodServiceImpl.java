package cn.hiczy.product.service;

import cn.hiczy.dto.product.FoodSavaDto;
import cn.hiczy.dto.product.PageInfoDto;
import cn.hiczy.entity.product.Food;
import cn.hiczy.product.dao.FoodMapper;
import cn.hiczy.service.product.IFoodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *  Food服务实现类
 *
 * @author saco
 * @since 2020-01-14
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {

    @Autowired
    FoodMapper foodMapper;

    @Override
    public List<Food> selectFoodListByRid(Long rid, PageInfoDto pageInfoDto) {
        Page<Food> page = new Page<>(pageInfoDto.getPageNumber(), pageInfoDto.getCount());
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("r_id", rid);
        foodMapper.selectPage(page, queryWrapper);
        return page.getRecords();
    }

    @Override
    public List<Food> selectFoodListByCid(Long cid) {
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_id", cid);
        List<Food> foodList = foodMapper.selectList(queryWrapper);
        return foodList;
    }

    @Override
    public Boolean saveFoodInfo(FoodSavaDto foodSaveDto) {
        Food food = new Food();
        BeanUtils.copyProperties(foodSaveDto, food);

        this.saveOrUpdate(food);
        return true;
    }

}
