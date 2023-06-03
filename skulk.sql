#redis
。signup
id，名字，密码,（1，100）
写redis，
    user|id
客户端sqlLite初始化，
    friends表、messageStore表
。signIn{，过期时间：7天
    key：user|id
    value：userName|password|level|money
}
加载，后端的propInfo表，
1级开始
战斗时，血量=level*10
例子，用户
    user|13112312312
    王五|123|1|10|500
例子：背包
写入道具和道具数量{
    HSET bag|cli1 道具1 5
    HSET bag|cli1 道具2 3
    HGETALL bag|cli1，查看cli1背包里所有的道具
    HSET bag|cli1 道具1 3，修改道具数量
}
朋友表，查本地sql-lite，friends表
    客户端无数据就从后端获取json串，写表
消息记录，查本地sql-lite，messageStore表
    客户端无数据就从后端获取json串，写表


client发起match，写redis，50m之内{
    key：Position|城市|区域|id1，
    value：定位天桥
}-》gateway-》game-》dbAgent

game（
get key
    key：城市|区域|*，返回一个
）-》dbAgent

未匹配的{
    key：Position|城市|区域|id1，
    value：定位天桥
}


{
    k，mached|城市|区域|
    v，
}
list，存放匹配失败用户（默认），
    北京，上海，，河北，
    每次新增节点前回调函数：
    1没有本区域，直接新增
    2有本区域，全部get出来，计算是否在对战区域内
        是，取出，设置成key：mached|城市|区域|
        否，新增本节点
list,匹配成功的，
    <天津1,天津2>
dbAgent，map<天津1,天津2>

dbAgent{

}-》game

game{单个用户位置}-》dbAgent
dbAgent计算匹配结果，
    key：mached|城市|区域|
    value：天津1|天津2

    key：mached|城市|区域
    value：id1|id2

game{获取匹配用户}-》dbAgent
dbAgent{value：id1|id2}-》game




dbAgent-》redis{

}

获取同一个区域的
dbAgent-》
天津|河西|id1

redis（
    key：城市|区域|*，返回第一个
）-》game

game（
    三个用户
）-》client

匹配时set：匹配完删除。
每500ms，调函数：更新，key：匹配|id1
。match：200ms更新
key：城市|区域|id1
value：定位天桥

匹配时更新：匹配完删除。
匹配超时：60s
key：匹配|id1
value：id3|id4|id5

50m，

geo，
500ms
key：天津|河东|id1
value：定位
key：天津|河东|id2
value：定位20m
key：天津|河东|id2
value：定位20m

key：天津|河东|id2
value：定位，100m

key：天津|河东|id2
value：定位

game
client match--(携带位置信息)-->gateway--(转发数据)-->mq
	--(从mq中拿到数据)-->game--(game本地存储map(

	key:client_userinfo_primary key
	    value:List[符合地理范围内的所有可战斗用户] 没有用户/初始化=NULL))-->
	--(从mq中拿到数据)-->dbAgent--(把用户位置信息存入redis(key:position|城市|区
	    value:具体方位))-->redis

battle：
key：能量-phoneNum
value：50


5
key：普攻-phoneNum
value：1
朋友列表
key：friend-phoneNum
value：friendId1-friendId2
#带ttl，过期
位置
5
key：pos-phoneNum
value：41.40338+2.17403
防御
key：defense-ttl|phoneNum
value：5

key：user|phoneNum
value：uid\passwd\energy\frinedList\probList\defenseState\pos

key：fight|phoneNum
value：

key：prop|phoneNum
value：道具1-道具2

key：prop-冷却|phoneNum
value：道具1-道具2

#mysql
-- id就是号码
CREATE TABLE `userInfo` (
  `id` varchar(20) NOT NULL,
--   `phoneNumber` varchar(20) NOT NULL,--唯一
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `level` INT NOT NULL ,--默认1
  `money` INT NOT NULL ,--默认100，每次战斗的时候-10，战斗赢了就+对方血量值数目的money/2。输了不扣
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `propInfo` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `price` (20) NOT NULL,
  -- `count` INT NOT NULL,
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
)
1，1，5
1，2，5
1，3，5


key：prop|id
value：name|price|attack|defense|bld|energy|probInfo

CREATE TABLE `friends` (
  userId INT NOT NULL,
  friendId INT NOT NULL,
  messageStoreFileName varchar(20) NOT NULL,
  PRIMARY KEY (user_id, friendId),
  FOREIGN KEY (user_id) REFERENCES userInfo(id),
  FOREIGN KEY (friendId) REFERENCES userInfo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
100，101,100+101.bak
100，102，
100，103，

客户端：
CREATE TABLE `propInfo` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `price` (20) NOT NULL,
  -- `count` INT NOT NULL,
  `attack` DECIMAL NOT NULL,
  `defense` DECIMAL NOT NULL,
  `bld` DECIMAL NOT NULL,
  `energy` DECIMAL NOT NULL,
  `probInfo` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `friends` (
  userId INT NOT NULL,
  friendId INT NOT NULL,
  messageStoreFileName varchar(20) NOT NULL,
  PRIMARY KEY (user_id, friendId),
  FOREIGN KEY (user_id) REFERENCES userInfo(id),
  FOREIGN KEY (friendId) REFERENCES userInfo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
101，100,100+101.bak
101，102，
101，103，
CREATE TABLE `messageStore` (
  fileName varchar(20),
  filePath varchar(50) NOT NULL,
  versionId varchar(50),
  PRIMARY KEY (fileName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
100+101.bak，/opt/apps/100+101.bak

im1，
文件名：userID（先发的）+UserID（后发的）.bak
===
时间，源,目的,内容
eg.2023年05月12日22:20:43|4|5|testtest
===
==
2023年05月12日22:20:43|4|5|testtest
2023年05月12日22:25:43|5|4|okok
==
disk：
  文件1
  文件2
  文件3
  文件4
  文件5
离线消息：
a->b，{
    key，offline|13112312312
    value，json{
2023年05月12日22:20:43|4|5|testtest
    }
}
#etcd
login：
机器1：192.168.1.1
机器2：192.168.1.1
机器3：192.168.1.1
gateway：
机器1：192.168.1.1
机器2：192.168.1.1
机器3：192.168.1.1
battle：
机器1：192.168.1.1
机器2：192.168.1.1
机器3：192.168.1.1
game：
机器1：192.168.1.1
机器2：192.168.1.1
机器3：192.168.1.1
dbAgent：
机器1：192.168.1.1
机器2：192.168.1.1
机器3：192.168.1.1
shop：
机器1：192.168.1.1
机器2：192.168.1.1
机器3：192.168.1.1










