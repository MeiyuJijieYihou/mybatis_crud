package cn.edu.csust.jt.dao;

import cn.edu.csust.jt.entity.Account;

import java.util.List;

/**
 * @Institution csust
 * @Author MeiyuJijieYihou
 * @Description Waiting fo development.
 * @Date 2020/2/11 下午6:19
 */
public interface AccountMapper {

    List<Account> selectAll();

    List<Account> selectAllLazy();
}
