CREATE DATABASE clientdb;
CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'test';
GRANT ALL PRIVILEGES ON clientdb.* TO 'testuser'@'localhost';

use clientdb;

create table client_user(
    id bigint auto_increment primary key,
    username varchar(100),
    password varchar(50),
    access_token varchar(100) NULL,
    access_token_validity datetime NULL,
    refresh_token varchar(100) NULL
);

insert into client_user
(username, password)
value
('bobo', 'xyz');

-- Spring Security升级到5.0以后，password需要加密存储，字段长度至少varchar(60)
ALTER TABLE `clientdb`.`client_user`
  CHANGE `password` `password` VARCHAR(60) CHARSET utf8 COLLATE utf8_general_ci NULL;
-- 对原始密码xyz，更新为使用BCrypt加密后的值
UPDATE `clientdb`.`client_user` SET password="$2a$10$KlzSHIdsd0q2jgPrsf2UTeluz./CWanknwX0qXFwSHJmX2DUvoGGG"
  WHERE username="bobo";