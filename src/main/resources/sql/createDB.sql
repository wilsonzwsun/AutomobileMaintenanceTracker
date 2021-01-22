-- create DB
DROP DATABASE IF EXISTS automobile_maintanence;
CREATE DATABASE automobile_maintanence;

USE automobile_maintanence;

DROP TABLE IF EXISTS `cars_tb`;
CREATE TABLE `cars_tb`(
`car_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'car id',
`vin` VARCHAR(120) NOT NULL COMMENT 'car vin',
`plate` CHAR(10) NOT NULL COMMENT 'car plate no.',
`maker` VARCHAR(64) NOT NULL COMMENT 'car maker',
`model` VARCHAR(64) NOT NULL COMMENT 'car model',
`type` VARCHAR (10)  NOT NULL COMMENT 'car type:electric,gas,diesel',
`odometer_reading` int NOT NULL COMMENT 'car current odometer_reading',
`produce_year` DATETIME NOT NULL COMMENT 'car produce year',
`status` varchar(10) NOT NULL COMMENT 'car status: normal,removed,....',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'record create time',
PRIMARY KEY (car_id,vin),
KEY idx_vin(vin),
KEY idx_plate(plate)
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='car information table';

INSERT INTO
   cars_tb (vin, plate, maker, model,type,odometer_reading,produce_year,status)
values
  ('ns23309139343it454', '800.nld', 'Nissan','pathfinder','gas','12340','2018-1-12 00:00:00','normal'),
  ('ty124430567334kln30', '400.8ul','Toyota', '4runner','gas','22334','2018-5-2 00:00:00','normal'),
  ('ts3430913d3435dfs5', 'e-204500', 'Tesla','model-y','electric','50033','2016-7-11 00:00:00','normal'),
  ('hd34390e99062jglv43', 'snx-220', 'Honda','civic','gas','3560','2020-8-26 00:00:00','normal'),
  ('fdd00343opi0038355000', 'fdd-150', 'Ford','F150','diesel','114910','2013-1-11 00:00:00','normal'),
  ('ty00343opi0038355000', 'fdd-150', 'Toyata','Prius','mixed','5003','2020-4-23 00:00:00','normal');

DROP TABLE IF EXISTS `cars_mt_tb`;
CREATE TABLE `cars_mt_tb`(
`task_id`BIGINT NOT NULL AUTO_INCREMENT COMMENT  'maintenance task id',
`car_id` BIGINT NOT NULL COMMENT 'car id',
`odometer_reading` int NOT NULL COMMENT 'car maintenance odometer_reading',
`task_name` VARCHAR(256) NOT NULL COMMENT 'car maintenance Task',
`maintenance_time` DATETIME NOT NULL,
`status` varchar(10) COMMENT 'record status: normal,removed,....',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'record create time',
PRIMARY  KEY (task_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='maintenance task table';
INSERT INTO
  cars_mt_tb (car_id, odometer_reading, task_name, maintenance_time,status)
values
  (10,24560,'tire rotation','2018-1-18 00:00:00','normal'),
  (10,28060,'change oil','2019-2-18 00:00:00','normal'),
  (11,34560,'tire rotation','2019-1-18 00:00:00','normal'),
  (11,34060,'change oil','2019-3-18 00:00:00','normal'),
  (12,55033,'tire rotation','2019-3-18 00:00:00','normal');
--
-- CREATE TABLE `cars_del`(
--  `car_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'car id',
--  `vin` VARCHAR(128) NOT NULL COMMENT 'car vin',
--  `plate` CHAR(10) NOT NULL COMMENT 'car plate no.',
--  `maker` VARCHAR(64) NOT NULL COMMENT 'car maker',
--  `model` VARCHAR(64) NOT NULL COMMENT 'car model',
--  `type` VARCHAR (10)  NOT NULL COMMENT 'car type:electric,gas,diesel',
--  `produce_year` DATETIME NOT NULL COMMENT 'car produce year',
--  `odometer_reading` int NOT NULL COMMENT 'car current odometer_reading',
--  `status` varchar(10) COMMENT 'car status',
--  `remove_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'record create time',
--  PRIMARY  KEY ("car_id","vin"),
--  KEY idx_vin(vin),
--  KEY idx_plate(plate)
-- )ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='car delete table';
