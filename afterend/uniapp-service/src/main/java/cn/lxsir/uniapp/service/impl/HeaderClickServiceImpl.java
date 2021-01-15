package cn.lxsir.uniapp.service.impl;

import cn.lxsir.entity.HeaderClick;
import cn.lxsir.uniapp.mapper.HeaderClickMapper;
import cn.lxsir.uniapp.service.HeaderClickService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Header栏 点击次数的记录 服务实现类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Service
public class HeaderClickServiceImpl extends ServiceImpl<HeaderClickMapper, HeaderClick> implements HeaderClickService {

}
