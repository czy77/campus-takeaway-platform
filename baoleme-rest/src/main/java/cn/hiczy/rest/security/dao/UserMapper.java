package cn.hiczy.rest.security.dao;


import cn.hiczy.rest.security.entity.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author saco
 */
public interface UserMapper  extends BaseMapper<User> {
    /**
     * 通过id 查询该用户所拥有的所有权限
     * @param id
     * @return
     */
    List<String> getAuthoritiesById(Long id);

}
