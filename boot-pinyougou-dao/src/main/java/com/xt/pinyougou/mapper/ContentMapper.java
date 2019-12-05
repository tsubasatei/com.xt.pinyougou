package com.xt.pinyougou.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xt.pinyougou.config.MybatisRedisCache;
import com.xt.pinyougou.pojo.Content;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
//@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface ContentMapper extends BaseMapper<Content> {

}
