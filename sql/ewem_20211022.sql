ALTER TABLE `ewem`.`ewem_apply`
ADD COLUMN `anti_rule` varchar(30) NULL COMMENT '防伪码规则' AFTER `batch_id`,
ADD COLUMN `anti_length` int(10) NULL COMMENT '防伪码长度' AFTER `anti_rule`;
ALTER TABLE `ewem`.`ewem_code`
ADD COLUMN `anti_code` varchar(50) NULL COMMENT '防伪码';