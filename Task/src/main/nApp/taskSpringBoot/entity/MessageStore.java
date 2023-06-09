package taskSpringBoot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzz
 * @Date 29/05/2023
 */
/* fileName  | varchar(20) | NO   | PRI | NULL    |       |
| filePath  | varchar(50) | NO   |     | NULL    |       |
| versionId | varchar(41)*/
@Data
@NoArgsConstructor
public class MessageStore {
    String fileName;
    String filePath;
    String versionId;
}
