package cn.huangnengxin.annotation;

import java.lang.annotation.*;

/**
 * Created by viruser on 2018/8/29.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
}
