package taskSpringBoot;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zzz
 * @Date 02/06/2023
 */
/* fileName  | varchar(20) | NO   | PRI | NULL    |       |
| filePath  | varchar(50) | NO   |     | NULL    |       |
| versionId | varchar(41)*/
@Mapper
public interface MessageStoreMapper {
    @Insert("insert into messageStore(fileName,filePath,versionId) values(#{fn},#{fp},#{vid});")
    int insert(@Param("fn") String fileName,
               @Param("fp") String filePath,
               @Param("vid") String versoinId);

}
