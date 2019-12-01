package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.mapper.GoodsDescMapper;
import com.xt.pinyougou.pojo.GoodsDesc;
import com.xt.pinyougou.service.GoodsDescService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-28
 */
@Service(timeout = 5000)
public class GoodsDescServiceImpl extends ServiceImpl<GoodsDescMapper, GoodsDesc> implements GoodsDescService {

}
