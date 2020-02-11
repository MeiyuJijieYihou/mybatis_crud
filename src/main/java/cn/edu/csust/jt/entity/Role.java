package cn.edu.csust.jt.entity;

import lombok.Data;

import java.util.List;

/**
 * @Institution csust
 * @Author MeiyuJijieYihou
 * @Description Waiting fo development.
 * @Date 2020/2/11 下午8:58
 */
@Data
public class Role {

    private Long id;
    private String role_name;

    private List<User> users;
}
