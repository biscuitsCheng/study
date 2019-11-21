package com.biscuits.doublecache.service;

import com.biscuits.doublecache.cache.CacheHelper;
import com.biscuits.doublecache.entity.Entity;
import com.biscuits.doublecache.mapper.IEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuits
 * @date 2019/9/30
 */
@Service
public class EntityServiceImpl implements IEntityService {
    private IEntityMapper entityMapper;

    @Override
    @CacheHelper(key = "entity")
    public Entity selectById(long id) {
        return entityMapper.selectById(id);
    }

    @Override
    public void test() {

    }

    @Autowired
    private void setEntityMapper(IEntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }
}
