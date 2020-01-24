package cn.edu.csust;

import cn.edu.csust.jt.dao.IUserMapper;
import cn.edu.csust.jt.entity.QueryVo;
import cn.edu.csust.jt.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Test {


    InputStream in = null;
    SqlSession sqlSession = null;
    IUserMapper userMapper = null;


    @Before
    public void init() throws Exception {
        // 1、读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("mybatisConfig.xml");

        // 2、获取sessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        // 3、获取SqlSession对象
        sqlSession = factory.openSession();

        // 4、获取Dao代理对象
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }


    @After
    public void destroy() throws IOException {

        // 6、提交事务
        sqlSession.commit();

        // 7、释放资源
        sqlSession.close();
        in.close();
    }


    @org.junit.Test
    public void testFindAll() {

        // 5、执行查询所有的方法
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }


    @org.junit.Test
    public void testSaveUser() {
        User user = new User();
        user.setUser_name("test_save");
        user.setBirthday(new Date());
        user.setAge(2);
        user.setPhone("32424");
        System.out.println(user);
        userMapper.saveUser(user);
        System.out.println(user);

    }


    @org.junit.Test
    public void testUpdate() {
        User user = new User();
        user.setId(5);
        user.setUser_name("test_update");
        user.setBirthday(new Date());
        user.setAge(12);
        user.setPhone("232432sa");
        userMapper.updateUser(user);
    }


    @org.junit.Test
    public void testDelete() {
        userMapper.deleteUser(5);
    }


    @org.junit.Test
    public void testFindById() {
        User user = userMapper.findById(2);
        System.out.println(user);
    }


    @org.junit.Test
    public void testFindByName() {
//        List<User> user = userMapper.findByName("%三%");
        List<User> user = userMapper.findByName("三");
        System.out.println(user);
    }

    @org.junit.Test
    public void testFindTotal() {
        Integer total = userMapper.findTotal();
        System.out.println(total);
    }

    @org.junit.Test
    public void testFindByUserVo() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUser_name("%三%");
        queryVo.setUser(user);

        List<User> users = userMapper.findByUserVo(queryVo);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

}