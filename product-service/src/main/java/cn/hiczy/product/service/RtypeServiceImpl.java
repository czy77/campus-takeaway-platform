package cn.hiczy.product.service;


import cn.hiczy.entity.product.Rtype;
import cn.hiczy.product.dao.RtypeMapper;
import cn.hiczy.service.product.IRtypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author saco
 * @since 2020-01-14
 */
@Service
public class RtypeServiceImpl extends ServiceImpl<RtypeMapper, Rtype> implements IRtypeService {

}
