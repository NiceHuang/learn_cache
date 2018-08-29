package cn.huangnengxin.dao.mysql;

import cn.huangnengxin.common.core.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by viruser on 2018/8/29.
 */
public interface DataPortralUserDao {

    @Select("SELECT * FROM data_portral_user")
    List<User> fetchUser();
}
