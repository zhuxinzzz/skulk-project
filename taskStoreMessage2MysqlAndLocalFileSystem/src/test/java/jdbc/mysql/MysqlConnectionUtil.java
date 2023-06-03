//package task.jdbc.mysql;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
///**
// * @author wei.jiang
// * @since 2018/11/24
// * 2023年05月29日
// */
//public class MysqlConnectionUtil {
//
//    private static MysqlConnectionUtil connectionUtil = new MysqlConnectionUtil();
//
//    public static Connection connection;
//
//    private MysqlConnectionUtil() {
//
//    }
//
//    public static MysqlConnectionUtil getInstance() {
//        if (connectionUtil == null) {
//            synchronized (MysqlConnectionUtil.class) {
//                if (connectionUtil == null) {
//                    connectionUtil = new MysqlConnectionUtil();
//                }
//            }
//        }
//        return connectionUtil;
//    }
//
//
//    @Test
//    public void testGetInstance() {
//        try {
//            connection = getConnection();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        Assertions.assertNotNull(connection);
//    }
//
//    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
//        //从配置文件里把相应的配置读出来
////        File config = new File("src/mysql.properties");
//        File config = new File("src/main/resources/mysql.properties");
//        FileInputStream fis = new FileInputStream(config);
//        Properties properties = new Properties();
//        properties.load(fis);
//        String jdbcDriver;
//        //主机
//        String jdbcUrl;
//        //数据库用户名
//        String userName;
//        String password;
//        jdbcDriver = properties.getProperty("DRIVER");
//        jdbcUrl = properties.getProperty("URL");
//        userName = properties.getProperty("USERNAME");
//        password = properties.getProperty("PASSWORD");
//        Class.forName(jdbcDriver);
//        connection = DriverManager.getConnection(jdbcUrl, userName, password);
//        return connection;
//
//    }
//
//}
