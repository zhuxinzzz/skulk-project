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



}
