package com.biscuits.doublecache.mapper;

import com.biscuits.doublecache.entity.Entity;


/**
 * @author biscuits
 * @date 2019/9/30
 */

public interface IEntityMapper {

    Entity selectById(long id);
}
