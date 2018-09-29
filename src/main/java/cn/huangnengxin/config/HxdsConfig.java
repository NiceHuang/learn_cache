package cn.huangnengxin.config;

import cn.huangnengxin.common.utils.DataSourceTypeEnum;
import cn.huangnengxin.config.router.RoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by viruser on 2018/9/19.
 */

@Configuration
@MapperScan(basePackages = "cn.huangnengxin.dao.mysql.slave", sqlSessionFactoryRef = "hxdsSqlSessionFactory")
public class HxdsConfig {

    @Autowired
    private ApplicationContext context;

    private Integer readSize = 1;

    @Autowired
    private DataSource write;

    @Bean(name = "read")
    @ConfigurationProperties(prefix = "spring.datasource.hxds")
    public DataSource hxdsDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hxdsSqlSessionFactory")
    public SqlSessionFactory hxdsSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(hxdsDataSource());
        return bean.getObject();
    }

    @Bean(name = "hxdsDataSourceTransactionManager")
    public DataSourceTransactionManager hxdsDataSourceTransactionManager(){
        return new DataSourceTransactionManager(hxdsDataSource());
    }

    @Bean(name = "hxdsSqlSessionTemplate")
    public SqlSessionTemplate hxdsSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(hxdsSqlSessionFactory());
    }

    @Bean
    public AbstractRoutingDataSource routingDataSourceProxy() {
        RoutingDataSource proxy = new RoutingDataSource(readSize);
        Map<Object, Object> targetDataSources = new HashMap<>(readSize + 1);
        targetDataSources.put(DataSourceTypeEnum.WRITE.getType(), write);
        for (int i = 0; i < readSize; i++) {
            DataSource d = context.getBean("read", DataSource.class);
            targetDataSources.put(i, d);
        }
        proxy.setDefaultTargetDataSource(write);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}
