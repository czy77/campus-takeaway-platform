package cn.hiczy.rest.controller.product;

import cn.hiczy.entity.product.Category;
import cn.hiczy.service.product.ICategoryService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCategoryController {
//    @Autowired
//    CategoryController categoryController;

    @Reference(check = false)
    ICategoryService categoryService;

    @Test
    public void test01() {
        List<Category> categories = categoryService.selectCategoriesByRid(1L);
        categories.stream().forEach(System.out::println);
    }

}
