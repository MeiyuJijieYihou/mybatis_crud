package cn.edu.csust.jt.dao;

import cn.edu.csust.jt.entity.QueryVo;
import cn.edu.csust.jt.entity.User;

import java.util.List;

public interface IUserMapper {

    /**
     * 查询所有用户信息的操作
     * @return
     */
    List<User> findAll();


    /**
     * 保存用户数据
     */
    void saveUser(User user);

    /**
     * 更新操作
     * @param user
     */
    void updateUser(User user);


    /**
     * 删除操作
     * @param uid
     */
    void deleteUser(Integer uid);


    /**
     * 根据id查询用户信息
     * @param uid
     */
    User findById(Integer uid);


    /**
     * 根据用户姓名查询用户信息
     * @param name
     * @return
     */
    List<User> findByName(String name);


    /**
     * 查找总记录条数
     * @return
     */
    Integer findTotal();


    /**
     * 通过Vo对象查询数据
     * @return
     */
    List<User> findByUserVo(QueryVo queryVo);

}
