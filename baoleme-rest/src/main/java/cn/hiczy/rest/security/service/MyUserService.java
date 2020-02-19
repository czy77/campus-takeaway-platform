package cn.hiczy.rest.security.service;

import cn.hiczy.rest.security.constant.RoleType;
import cn.hiczy.rest.security.dao.UserMapper;
import cn.hiczy.rest.security.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saco
 */
@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //从数据库查出对应的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", s)
                .or()
                .eq("phone_number", s);
        User user = userMapper.selectOne(queryWrapper);

        //从数据库中查询此用户的所有权限
        List<String> permissonList = userMapper.getAuthoritiesById(user.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String p : permissonList) {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        }
        user.setAuthorities(authorities);
        return user;
    }


    public boolean signUp(User user, boolean accountType) {
        //将用户的密码加密后存入数据库
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Long roleId = -1L;
        roleId = accountType ? RoleType.NORMAL_USER : RoleType.OWNER;
        user.setRoleId(roleId);

        userMapper.insert(user);

        return true;
    }



    public void setKeyWithExpiration(String key, String token,long expireTime) {
        stringRedisTemplate.opsForValue().set(key, token, expireTime);
    }

    public String getValueFromRedis(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }


}
