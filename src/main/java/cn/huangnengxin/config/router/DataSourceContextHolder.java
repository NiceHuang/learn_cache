package cn.huangnengxin.config.router;

import cn.huangnengxin.common.utils.DataSourceTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by viruser on 2018/9/19.
 */
public class DataSourceContextHolder {

    private static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private final static ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    public static void read() {
        logger.info("切换至[读]数据源");
        local.set(DataSourceTypeEnum.READ.getType());
    }

    public static void write() {
        logger.info("切换至[写]数据源");
        local.set(DataSourceTypeEnum.WRITE.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
