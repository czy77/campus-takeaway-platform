package cn.hiczy.service.product;


import cn.hiczy.dto.product.CategorySaveDto;
import cn.hiczy.entity.product.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  category服务类
 * </p>
 *
 * @author saco
 * @since 2020-01-14
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 根据商铺返回 category列表
     * @param rid
     * @return
     */
    List<Category> selectCategoriesByRid(Long rid);

    /**
     * 新增或修改标签信息
     * @param categorySaveDto
     * @return
     */
    Boolean saveCategoryInfo(CategorySaveDto categorySaveDto);



}
