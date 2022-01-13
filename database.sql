-- DROP DATABASE IF  EXISTS `coodle`;
CREATE DATABASE IF NOT EXISTS `coodle`;
USE `coodle`;

-- CREATE TABLE `users` (
--     `id` INT NOT NULL AUTO_INCREMENT,
--     `email` VARCHAR(255) NOT NULL,
--     `password` VARCHAR(255) NOT NULL,
--     `first_name` VARCHAR(255) NOT NULL,
--     `last_name` VARCHAR(255) NOT NULL,
--     `reset_password_token` VARCHAR(45),
--     PRIMARY KEY (`id`)
-- )  ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;


-- CREATE TABLE `roles` (
--     `id` INT NOT NULL AUTO_INCREMENT,
--     `role` VARCHAR(255) DEFAULT NULL,
--     PRIMARY KEY (`id`)
-- )  ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

INSERT INTO roles(role) VALUES('ROLE_USER');
INSERT INTO roles(role) VALUES('ROLE_MODERATOR');
INSERT INTO roles(role) VALUES('ROLE_ADMIN');

-- CREATE TABLE `users_roles` (
--     `user_id` INT NOT NULL,
--     `role_id` INT NOT NULL,
--     PRIMARY KEY (`user_id` , `role_id`),
--     KEY `fk_user_roles_role_id_roles_id` (`role_id`),
--     CONSTRAINT `fk_user_roles_role_id_roles_id` FOREIGN KEY (`role_id`)
--         REFERENCES `roles` (`id`),
--     CONSTRAINT `fk_user_roles_user_id_users_id` FOREIGN KEY (`user_id`)
--         REFERENCES `users` (`id`)
-- )  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;


-- INSERT INTO `coodle`.`roles`(`id`,`role`)VALUES(1,'ADMIN');
-- INSERT INTO `coodle`.`users_roles`(`user_id`,`role_id`)VALUES (1,1);

