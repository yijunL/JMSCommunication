package dao;

import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class UserDao {
    String resource;
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;

    public UserDao() throws IOException {
        resource = "configuration.xml";
        // 读取文件字节流
        inputStream = Resources.getResourceAsStream(resource);

        // mybatis 读取字节流，利用XMLConfigBuilder类解析文件
        // 将xml文件解析成一个 org.apache.ibatis.session.Configuration 对象
        // 然后将 Configuration 对象交给 SqlSessionFactory 接口实现类 DefaultSqlSessionFactory 管理
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }
    public boolean register(){
        System.out.println("enter your userId and password to register.(/q to quit)");
        Scanner sc=new Scanner(System.in);
        String userId=sc.next();
        String password=sc.next();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> res=userMapper.findAllByUsername(userId);
        while(!res.isEmpty()){
            System.out.println("This user ID is already in, try another (/q to exist)");
            userId=sc.next();
            if("/q".equalsIgnoreCase(userId)){
                System.exit(0);
            }
            password=sc.next();
            res=userMapper.findAllByUsername(userId);
        }
        User user=new User();
        user.setUsername(userId);
        user.setPassword(password);
        userMapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("Registration succeeded! ");
        return true;
    }


    public boolean check(String username,String password){
        // openSession 有多个重载方法， 比较重要几个是
        // 1 是否默认提交 SqlSession openSession(boolean autoCommit)
        // 2 设置事务级别 SqlSession openSession(TransactionIsolationLevel level)
        // 3 执行器类型   SqlSession openSession(ExecutorType execType)
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // mybatis 内部其实已经解析好了 mapper 和 mapping 对应关系，放在一个map中，这里可以直接获取
        // 如果看源码可以发现userMapper 其实是一个代理类MapperProxy，
        // 通过 sqlSession、mapperInterface、mechodCache三个参数构造的
        // MapperProxyFactory 类中 newInstance(MapperProxy<T> mapperProxy)方法
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> res=userMapper.findAllByUsernameAndPassword(username,password);
        // 由于默认 openSession() 事务是交由开发者手动控制，所以需要显示提交
        sqlSession.commit();
        sqlSession.close();
        if(res.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}