package com.jbwz.core.mybatis.base;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


public class BaseServiceImpl<M extends BaseDao<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
}
