package cn.edu.csust;

import cn.edu.csust.jt.dao.IImparityUserMapper;
import cn.edu.csust.jt.dao.IUserMapper;
import cn.edu.csust.jt.entity.ImparityUser;
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

public class ImparityTest {


    InputStream in = null;
    SqlSession sqlSession = null;
    IImparityUserMapper imparityUserMapper = null;


    @Before
    public void init() throws Exception {
        // 1、读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("mybatisConfig.xml");

        // 2、获取sessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        // 3、获取SqlSession对象
        sqlSession = factory.openSession();

        // 4、获取Dao代理对象
        imparityUserMapper = sqlSession.getMapper(IImparityUserMapper.class);
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
        List<ImparityUser> users = imparityUserMapper.findAll();
        for (ImparityUser user : users) {
            System.out.println(user);
        }
    }


    @org.junit.Test
    public void testSaveUser() {
        ImparityUser user = new ImparityUser();
        user.setUserName("test_save");
        user.setUserBirthday(new Date());
        user.setUserAge(2);
        user.setUserPhone("32424");
        System.out.println(user);
        imparityUserMapper.saveUser(user);
        System.out.println(user);

    }


    @org.junit.Test
    public void testUpdate() {
        ImparityUser user = new ImparityUser();
        user.setUserId(5);
        user.setUserName("test_save");
        user.setUserBirthday(new Date());
        user.setUserAge(21);
        user.setUserPhone("sds3434");
        imparityUserMapper.updateUser(user);
    }


    @org.junit.Test
    public void testDelete() {
        imparityUserMapper.deleteUser(5);
    }


    @org.junit.Test
    public void testFindById() {
        ImparityUser user = imparityUserMapper.findById(2);
        System.out.println(user);
    }


    @org.junit.Test
    public void testFindByName() {
//        List<User> user = imparityUserMapper.findByName("%三%");
        List<ImparityUser> user = imparityUserMapper.findByName("三");
        System.out.println(user);
    }

    @org.junit.Test
    public void testFindTotal() {
        Integer total = imparityUserMapper.findTotal();
        System.out.println(total);
    }

    @org.junit.Test
    public void testFindByUserVo() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUser_name("%三%");
        queryVo.setUser(user);

        List<ImparityUser> users = imparityUserMapper.findByUserVo(queryVo);
        for (ImparityUser user1 : users) {
            System.out.println(user1);
        }
    }

}