package cn.huangnengxin.service.impl;

import cn.huangnengxin.annotation.RedisCache;
import cn.huangnengxin.common.core.User;
import cn.huangnengxin.dao.mysql.DataPortralUserDao;
import cn.huangnengxin.service.DataPortralUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/29.
 */

@Service
public class DataPortralUserServiceImpl implements DataPortralUserService {

    @Autowired
    private DataPortralUserDao dataPortralUserDao;

    @Override
    @RedisCache
    public List<User> fetchUser(String key, Map<String, Object> map) {
        return dataPortralUserDao.fetchUser();
    }
    @RedisCache
    public List<User> fetchUsers(String key, Map<String, Object> map) {
        return dataPortralUserDao.fetchUser();
    }
}
