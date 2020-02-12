package cn.edu.csust.jt.dao;

import cn.edu.csust.jt.entity.Role;
import cn.edu.csust.jt.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * @Institution csust
 * @Author MeiyuJijieYihou
 * @Description Waiting fo development.
 * @Date 2020/2/11 下午7:01
 */
public interface UserMapper {
    List<User> selectAll();

    List<User> selectAllUserRole();

    List<User> selectAllLazy();
}
