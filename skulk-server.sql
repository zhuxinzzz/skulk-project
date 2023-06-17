-- mysql
create database skulk;

-- id就是号码
CREATE TABLE `userInfo` (
  `id` varchar(20) NOT NULL,
--   `phoneNumber` varchar(20) NOT NULL,--唯一
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
   /* --默认1*/
  `level` INT NOT NULL ,
    /*--默认100，每次战斗的时候-10，战斗赢了就+对方血量值数目的money/2。输了不扣*/
  `money` INT NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into userInfo values('100','user1','123',1,100);
insert into userInfo values('101','user2','123',1,100);

--
CREATE TABLE `propInfo` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `price` varchar(20) NOT NULL,
  /*-- `count` INT NOT NULL,*/
  `attack` DECIMAL NOT NULL,
  `defense` DECIMAL NOT NULL,
  `bld` DECIMAL NOT NULL,
  `energy` DECIMAL NOT NULL,
  `probInfo` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bag` (
  userId varchar(20) NOT NULL,
  propId varchar(20) NOT NULL,
  count INT NOT NULL,
  PRIMARY KEY (userId, propId),
  FOREIGN KEY (userId) REFERENCES userInfo(id),
  FOREIGN KEY (propId) REFERENCES propInfo(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 1，1，5
-- 1，2，5
-- 1，3，5
CREATE TABLE `friends` (
  userId varchar(20) NOT NULL,
  friendId varchar(20) NOT NULL,
  messageStoreFileName varchar(50) NOT NULL,
  PRIMARY KEY (userId, friendId),
  FOREIGN KEY (userId) REFERENCES userInfo(id),
  FOREIGN KEY (friendId) REFERENCES userInfo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into friends values('100','101','100+101.bak');
insert into friends values('101','100','100+101.bak');
select messageStoreFileName from friends where userId='100' and friendId='101';
-- 100，101,113112312312+13112312313.bak
-- 100，102，
-- 100，103，
CREATE TABLE `messageStore` (
  fileName varchar(50),
  filePath varchar(50) NOT NULL,
  versionId varchar(50),
  PRIMARY KEY (fileName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into messageStore values('100+101.bak','100+101.bak','0');
-- 100+101.bak，/opt/apps/100+101.bak,da39a3ee5e6b4b0d3255bfef95601890afd80709
-- im1，
-- 文件名：userID（先发的）+UserID（后发的）.bak
-- ===
-- 时间，源,目的,内容
-- eg.2023年05月12日22:20:43|4|5|testtest
-- ===
-- ==
-- 2023年05月12日22:20:43|4|5|testtest
-- 2023年05月12日22:25:43|5|4|okok
-- ==
-- disk：
--   文件1
--   文件2
--   文件3
--   文件4
--   文件5
-- 离线消息：
-- a->b，{
--     key，offline|13112312312
--     value，json{
-- 2023年05月12日22:20:43|4|5|testtest
--     }
-- }








