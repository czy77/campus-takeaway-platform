package cn.hiczy.product.service;

import cn.hiczy.dto.product.CategorySaveDto;
import cn.hiczy.entity.product.Category;
import cn.hiczy.product.dao.CategoryMapper;
import cn.hiczy.service.product.ICategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public List<Category> selectCategoriesByRid(Long rid) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("r_id", rid);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return categories;
    }

    @Override
    public Boolean saveCategoryInfo(CategorySaveDto categorySaveDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categorySaveDto, category);
        boolean b = this.save(category);
        return b;
    }

}
