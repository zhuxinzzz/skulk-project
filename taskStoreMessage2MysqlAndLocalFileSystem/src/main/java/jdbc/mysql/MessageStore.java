package jdbc.mysql;

import lombok.Data;

/**
 * @author zzz
 * @Date 29/05/2023
 */
/* fileName  | varchar(20) | NO   | PRI | NULL    |       |
| filePath  | varchar(50) | NO   |     | NULL    |       |
| versionId | varchar(41)*/
@Data
public class MessageStore {
    String fileName;
    String filePath;
    String versionId;
}
