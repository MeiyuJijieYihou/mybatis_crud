package cn.edu.csust;

import cn.edu.csust.jt.dao.AccountMapper;
import cn.edu.csust.jt.dao.IImparityUserMapper;
import cn.edu.csust.jt.entity.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Institution csust
 * @Author MeiyuJijieYihou
 * @Description Waiting fo development.
 * @Date 2020/2/11 下午6:26
 */
public class AccountTest {

    InputStream in = null;
    SqlSession sqlSession = null;

    AccountMapper accountMapper = null;

    @Before
    public void init() throws Exception {
        // 1、读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("mybatisConfig.xml");

        // 2、获取sessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        // 3、获取SqlSession对象
        sqlSession = factory.openSession();

        // 4、获取Dao代理对象
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }


    @After
    public void destroy() throws IOException {

        // 6、提交事务
        sqlSession.commit();

        // 7、释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void findAll() {

        List<Account> accounts = accountMapper.selectAll();

        for (Account account: accounts) {
            System.out.println(account.toString());
        }
    }

}
