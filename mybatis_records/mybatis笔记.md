#	mybatis笔记（采用代理dao层）

## 1.mybatis基本配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 数据库配置   -->
    <environments default="mysql">
        <environment id="mysql">
            <transactionManager type="JDBC" />
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://127.0.0.1:3306/mybatis_learn?characterEncoding=utf8"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>

    <!-- mapper文件配置 -->
    <mappers>
        <mapper resource="mapper/IUserMapper.xml"/>
        <mapper resource="mapper/IImparityUserMapper.xml" />
    </mappers>
</configuration>
```

## 2. 模糊查询实现（mapper配置文件）:

```sql
(1)select *
        	from user
        	where user_name like #{name};

(2)select *
		from user
		where user_name like '%${value}%';(此种方式为字符串的拼接，少使用)
```

## 3. 查找最后一个插入的一条数据的ID。

### 3.1 Mapper配置文件如下:

```xml
<selectKey keyProperty="id" keyColumn="id" resultType="Integer" order="AFTER">
   	 	select last_insert_id();
</selectKey>
```

### 3.2 JUnit测试:

```java
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
```

### 3.3 测试结果：

``` java
User(id=null, user_name=test_save, birthday=Fri Jan 24 22:27:53 CST 2020, age=2, phone=32424)
User(id=10, user_name=test_save, birthday=Fri Jan 24 22:27:53 CST 2020, age=2, phone=32424)
```

## 4. mybatis中使用的是OGNL(Object Graphic Navigation Language)表达式语言。

### 4.1 获取普通实体类中的属性通过object.getName;而OGNL表达式语言通过object.name,多层调用可嵌套使用，如：object.name.name。

### 4.2 例子:

#### 4.2.1 Mapper配置文件如下：

```xml
<select id="findByUserVo" parameterType="cn.edu.csust.jt.entity.QueryVo" resultType="cn.edu.csust.jt.entity.User">
    	select *
    	from user
    	where user_name like #{user.user_name};
</select>
```

#### 4.2.2 测试类如下：

```java
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
```

#### 4.2.1 测试结果如下：

![image-20200124230703362](/home/dx/桌面/其他/mybatis/images/image-20200124230703362.png)

## 5. mybatis中实体类的属性名与数据库中的字段名不同，实现方法。
### 5.1 通过在sql语句中设置字段别名实现。

#### 5.1.1 mapper配置文件：

```xml
<select id="findAll" resultType="cn.edu.csust.jt.entity.ImparityUser">
        select id as userId, user_name as UserName, birthday as userBirthday, age as userAge, phone as userPhone
        from user;
</select>
```

#### 5.1.2 测试类：

```java
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

```

#### 5.1.3 测试结果：

![image-20200127220238650](/home/dx/桌面/其他/mybatis/images/image-20200127220238650.png)

### 5.2 在mapper文件中通过resultMap设置。

#### 5.2.1 mapper配置文件:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.edu.csust.jt.dao.IImparityUserMapper">

    <!-- 配置查询结果的列名和实体类的属性名的对应关系   -->
    <resultMap id="imparityUser" type="cn.edu.csust.jt.entity.ImparityUser">
        <!-- id字段的对应关系    -->
        <id property="userId" column="id"/>

        <!-- 各字段的对应关系    -->
        <result property="userName" column="user_name" />
        <result property="userBirthday" column="birthday" />
        <result property="userAge" column="age" />
        <result property="userPhone" column="phone" />
    </resultMap>


    <select id="findAll" resultType="cn.edu.csust.jt.entity.ImparityUser">
        select id as userId, user_name as UserName, birthday as userBirthday, age as userAge, phone as userPhone
        from user;
    </select>

    <insert id="saveUser" parameterType="cn.edu.csust.jt.entity.ImparityUser">

        <selectKey keyProperty="userId" keyColumn="id" resultType="Integer" order="AFTER">
            select last_insert_id();
        </selectKey>

        insert
        into user (user_name, birthday, age, phone)
        values (#{userName}, #{userBirthday}, #{userAge}, #{userPhone});
    </insert>

    <update id="updateUser" parameterType="cn.edu.csust.jt.entity.ImparityUser">
        update user
        set user_name = #{userName}, birthday = #{userBirthday}, age = #{userAge}, phone = #{userPhone}
        where id = #{userId};
    </update>

    <delete id="deleteUser">
        delete
        from user
        where id = #{uid};
    </delete>

    <select id="findById" parameterType="Integer" resultMap="imparityUser">
        select *
        from user
        where id = #{uid};
    </select>

    <select id="findByName" parameterType="String" resultMap="imparityUser">
        <!--        select *-->
        <!--        from user-->
        <!--        where user_name like #{name};-->

        select *
        from user
        where user_name like '%${value}%'
    </select>
    <select id="findTotal" resultType="java.lang.Integer">
        select count(id)
        from user;
    </select>

    <select id="findByUserVo" parameterType="cn.edu.csust.jt.entity.QueryVo" resultMap="imparityUser">
        select *
        from user
        where user_name like #{user.user_name};
    </select>
</mapper>
```

#### 5.2.2 测试类：

```java
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
//        List<User> user = imparityUserMapper.findByName("%三%");
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
```

#### 5.2.3 测试结果：

![image-20200127223555181](/home/dx/桌面/其他/mybatis/images/image-20200127223555181.png)