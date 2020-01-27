package cn.edu.csust.jt.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Institution csust
 * @Author MeiyuJijieYihou
 * @Description Waiting fo development.
 * @Date 2020/1/27 下午9:15
 */
@Data
public class ImparityUser {

    private Integer userId;
    private String userName;
    private Date userBirthday;
    private Integer userAge;
    private String userPhone;
}
