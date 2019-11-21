package com.biscuits.doublecache.mapper;

import com.biscuits.doublecache.entity.Entity;
import com.biscuits.doublecache.mapper.IEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author biscuits
 * @date 2019/9/30
 */
@Component
@Slf4j
public class EntityMapperImpl implements IEntityMapper {
    @Override
    public Entity selectById(long id) {
        log.info("查询数据库");
        List<Entity> list = Arrays.asList(new Entity(1L, "张三"), new Entity(2L, "李四"));
        Optional<Entity> entityOp = list.stream().filter(Objects::nonNull).filter(o -> id == o.getId()).findAny();
        return entityOp.orElseGet(null);
    }
}
