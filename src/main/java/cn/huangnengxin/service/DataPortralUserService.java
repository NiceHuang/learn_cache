package cn.huangnengxin.service;

import cn.huangnengxin.common.core.User;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/29.
 */
public interface DataPortralUserService {


    List<User> fetchUser(String key, Map<String, Object> map);

}
