package cn.edu.csust.jt.dao;

import cn.edu.csust.jt.entity.ImparityUser;
import cn.edu.csust.jt.entity.QueryVo;
import cn.edu.csust.jt.entity.User;

import java.util.List;

/**
 * @Institution csust
 * @Author MeiyuJijieYihou
 * @Description Waiting fo development.
 * @Date 2020/1/27 下午9:14
 */
public interface IImparityUserMapper {

    /**
     * 查询所有用户信息的操作
     * @return
     */
    List<ImparityUser> findAll();


    /**
     * 保存用户数据
     */
    void saveUser(ImparityUser imparityUser);

    /**
     * 更新操作
     * @param imparityUser
     */
    void updateUser(ImparityUser imparityUser);


    /**
     * 删除操作
     * @param uid
     */
    void deleteUser(Integer uid);


    /**
     * 根据id查询用户信息
     * @param uid
     */
    ImparityUser findById(Integer uid);


    /**
     * 根据用户姓名查询用户信息
     * @param name
     * @return
     */
    List<ImparityUser> findByName(String name);


    /**
     * 查找总记录条数
     * @return
     */
    Integer findTotal();


    /**
     * 通过Vo对象查询数据
     * @return
     */
    List<ImparityUser> findByUserVo(QueryVo queryVo);
}
