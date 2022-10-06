package com.hu.qingshan.annotation;

import java.lang.annotation.*;

/**
 * 是否自动填充创建时间
 * 由于实体类对象继承的关系，使用类注解来说明一个类是否需要被自动填充
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateDate {
}
