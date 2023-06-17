package org.dao.mysql.pojo;

import lombok.Data;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.dao.mysql.mybais.mapper.IFriendsMapper;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author zzz
 * @Date 16/06/2023
 */
@Data
public class Friends {
    String userId;
    String friendId;
    String messageStoreFileName;


    /**
     * 测试 Java 方式
     */
    @Test
    public void testJavaConfig() {
        // 创建数据源
        DataSource dataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://s1:3306/skulk?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                "root",
                "QwErmysql1!");
        //事务
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //环境
        Environment environment = new Environment("development", transactionFactory, dataSource);
        //配置
        Configuration configuration = new Configuration(environment);
        //注册
        configuration.addMapper(IFriendsMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IFriendsMapper iFriendsMapper = sqlSession.getMapper(IFriendsMapper.class);

        //插入 user
//        IUserMapper.insertUser(new User(2, "ConstXiong2"));
        //查询该 user
//        User user = IUserMapper.selectUser(2);
//        System.out.println(user);
        //查询所有 user 数据
        String fileName = iFriendsMapper.selectMessageStoreFileName("100", "101");
        System.out.println(fileName);

        //关闭 SqlSession
        sqlSession.close();
    }
}
