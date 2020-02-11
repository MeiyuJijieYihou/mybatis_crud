package cn.edu.csust.jt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Institution csust
 * @Author MeiyuJijieYihou
 * @Description Waiting fo development.
 * @Date 2020/2/11 下午6:01
 */
@Data
public class Account implements Serializable {
    private Long id;
    private Long uid;
    private Integer money;
    private User user;
}
