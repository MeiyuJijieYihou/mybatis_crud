#	mybatis笔记

## 1. 模糊查询:

```sql
(1)select *
        	from user
        	where user_name like #{name};

(2)select *
		from user
		where user_name like '%${value}%';(此种方式为字符串的拼接，少使用)
```

## 1. 查找最后一个插入的一条数据的ID为：

### 2.1 Mapper配置文件如下：

```xml
<selectKey keyProperty="id" keyColumn="id" resultType="Integer" order="AFTER">
   	 	select last_insert_id();
</selectKey>
```

### 2.2 JUnit测试

```java
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

### 2.3 测试结果：

``` java
User(id=null, user_name=test_save, birthday=Fri Jan 24 22:27:53 CST 2020, age=2, phone=32424)
User(id=10, user_name=test_save, birthday=Fri Jan 24 22:27:53 CST 2020, age=2, phone=32424)
```

## 3. mybatis中使用的是OGNL(Object Graphic Navigation Language)表达式语言:

### 3.1 获取普通实体类中的属性通过object.getName;而OGNL表达式语言通过object.name,多层调用可嵌套使用，如：object.name.name。

### 3.2 例子：

#### 3.2.1 Mapper配置文件如下：

```xml
<select id="findByUserVo" parameterType="cn.edu.csust.jt.entity.QueryVo" resultType="cn.edu.csust.jt.entity.User">
    	select *
    	from user
    	where user_name like #{user.user_name};
</select>
```

#### 3.2.2 测试类如下：

```java
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

#### 3.2.1 测试结果如下：

![image-20200124230703362](/home/dx/桌面/其他/mybatis/images/image-20200124230703362.png)