package cn.edu.csust.jt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {

    private Integer id;
    private String user_name;
    private Date birthday;
    private Integer age;
    private String phone;

    private List<Account> accounts;

    private List<Role> roles;
}
