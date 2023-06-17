package org.dao.mysql.mybais.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author zzz
 * @Date 16/06/2023
 */
public interface IFriendsMapper {
    @Select("select messageStoreFileName from friends where userId=#{userId} and friendId=#{friendId}")
    String selectMessageStoreFileName(String userId, String friendId);

}
