package com.biscuits.algorithm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuits
 * @date 2019/11/11
 */
@Builder
@Getter
@Setter
public class A {
    private String name;
    private String psword;
    @Singular
    private List<B> values;
}
