package cn.huangnengxin.aop;

import cn.huangnengxin.config.router.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by viruser on 2018/9/19.
 */
@Component
@Aspect
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(cn.huangnengxin.annotation.Master)")
    public void pointcutMaster(){

    }

    @Before("pointcutMaster()")
    public void setMasterDataSource(){
        LOGGER.info("拦截[write]方法");
        DataSourceContextHolder.write();
    }

    @Pointcut("@annotation(cn.huangnengxin.annotation.Slave)")
    public void pointcutSlave(){

    }


    @Before("pointcutSlave()")
    public void setSlaveDataSource(){
        LOGGER.info("拦截[read]操作");
        DataSourceContextHolder.read();
    }
}
