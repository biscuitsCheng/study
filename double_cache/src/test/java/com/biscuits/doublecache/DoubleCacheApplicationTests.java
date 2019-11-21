package com.biscuits.doublecache;

import com.biscuits.doublecache.entity.Entity;
import com.biscuits.doublecache.service.IEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoubleCacheApplicationTests {
    @Autowired
    private IEntityService entityService;
    @Test
    public void contextLoads() {
        Entity entity = entityService.selectById(1);
        System.out.println(entity);
        Entity entity2 = entityService.selectById(1);
        System.out.println(2);

    }

}
