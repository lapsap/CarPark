CREATE DATABASE IF NOT EXISTS lapsapcarpark CHARACTER SET utf8;
USE lapsapcarpark;

DROP TABLE IF EXISTS lapsapticket;
DROP VIEW IF EXISTS lapsapticket;
CREATE TABLE lapsapticket (
   `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` char(5) NULL ,
`date` char(10) NULL ,
`parkingfee` char(5) NULL ,
`entrytime` char(5),
`exittime` char(5) null,
`comment` text null ,
 PRIMARY KEY (`id`)
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;