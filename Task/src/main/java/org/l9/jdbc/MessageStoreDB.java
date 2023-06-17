package org.l9.jdbc;


import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zzz
 * @Date 29/05/2023
 */
public class MessageStoreDB {
    public String getMD5(String input) {
        try {
            // 创建 MessageDigest 对象，指定为 MD5算法
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换为字节数组，并计算其哈希值
            byte[] inputBytes = input.getBytes();
            byte[] hashBytes = md.digest(inputBytes);

            // 将哈希值转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // 如果指定的算法不存在，则抛出异常
            throw new RuntimeException(e);
        }
    }

    //filename,path,versionId
    public int insertRow(String fn, String fp, String vid) {

        Connection connection = null;
        try {
            connection = MysqlConnectionUtil.getInstance().getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        insert into messageStore(fileName,filePath,versionId) values('aa','bb','cc');
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into messageStore(fileName,filePath,versionId) values(?,?,?);");
            String[] pps = {fn, fp, vid};
            for (int i = 0; i < pps.length; i++) {
                preparedStatement.setString(i + 1, pps[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
