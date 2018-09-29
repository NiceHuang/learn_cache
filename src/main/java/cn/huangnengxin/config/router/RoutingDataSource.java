package cn.huangnengxin.config.router;

import cn.huangnengxin.common.utils.DataSourceTypeEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by viruser on 2018/9/19.
 */
public class RoutingDataSource extends AbstractRoutingDataSource {


    private AtomicInteger count = new AtomicInteger(0);

    private int readSize;

    public RoutingDataSource(int readSize) {
        this.readSize = readSize;
    }
    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        if (typeKey == null) {
            logger.error("无法确定数据源");
        }
        if (typeKey.equals(DataSourceTypeEnum.WRITE.getType())) {
            return DataSourceTypeEnum.WRITE.getType();
        }
        //读库进行负载均衡
        int a = count.getAndAdd(1);
        int lookupKey = a % readSize;
        return lookupKey;
    }
}
