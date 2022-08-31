package com.hu.qingshan.core.convert;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

/**
 * 通用转换器，转换各种实体
 */
public abstract class ModelConvert {

    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private final MapperFacade mapperFacade = mapperFactory.getMapperFacade();

    protected <S,T> List<T> ConvertToListTarget(List<S> source,Class<T> target){
        return mapperFacade.mapAsList(source,target);
    }

//    为什么这里是范型class
    protected <S,T> T ConvertToTarget(S source,Class<T> target){
        return mapperFacade.map(source,target);
    }

}
