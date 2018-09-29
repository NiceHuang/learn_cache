package cn.huangnengxin.dao.mysql.master;

import cn.huangnengxin.common.core.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by viruser on 2018/8/29.
 */
public interface DataPortralUserDao {

    @Select("SELECT * FROM data_portral_user")
    List<User> fetchUser();


    @Insert("INSERT INTO data_portral_user(`username`, `email`, `password`, `status`) " +
            "VALUES(#{username}, #{email}, #{password}, #{status})")
    void addUser(User user);
}
