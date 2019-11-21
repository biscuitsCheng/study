package com.biscuits.doublecache.controller;

import com.biscuits.doublecache.entity.Entity;
import com.biscuits.doublecache.service.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author biscuits
 * @date 2019/10/1
 */
@RestController
@RequestMapping("/entity")
public class EntityController {
    private IEntityService entityService;

    @GetMapping("/{id}")
    public Entity selectById(@PathVariable long id) {
        return entityService.selectById(id);
    }

    @Autowired
    private void setEntityService(IEntityService entityService) {
        this.entityService = entityService;
    }


}
