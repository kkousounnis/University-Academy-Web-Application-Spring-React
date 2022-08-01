-- First create databse then run project with spring boot,
-- the database will be created automatically with ORM technology
CREATE DATABASE IF NOT EXISTS `coodle`;

-- then insert the roles
USE `coodle`;

INSERT INTO `roles`(`role`) VALUES('ROLE_USER');
INSERT INTO `roles`(`role`) VALUES('ROLE_MODERATOR');
INSERT INTO `roles`(`role`) VALUES('ROLE_ADMIN');

use `coodle`;
INSERT INTO `courses`(`title`,`semester`,`price`) VALUES('ALGORITHMS','A',250);
INSERT INTO `courses`(`title`,`semester`,`price`) VALUES('ANALYSIS','A',250);
INSERT INTO `courses`(`title`,`semester`,`price`) VALUES('PYTHON','B',200);
INSERT INTO `courses`(`title`,`semester`,`price`) VALUES('DATABASES','B',200);
INSERT INTO `courses`(`title`,`semester`,`price`) VALUES('MASTER_THESIS','B',400);

-- We will create one admin you user who will have access to the admin page.
-- In order to create an admin password you must run Spring boot Password 
-- encryption service and post a strong password in order to be encrypted.
-- New Admin password: T^j>bb/(-(62S~9m

use `coodle`;
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO `users`(`email`,`first_name`,`last_name`,`password`)
 VALUES('admin@coodle.com','admin','admin','$2a$10$Ov56CV8sQPjNcv4vOQ3tTuEf2vnEah3ahxATdaFWPa6Pg.Kdw0G9e');
INSERT INTO `users_roles`(`role_id`,`user_id`) VALUES (23,3);


-- First Trainer Password: A@er%/(-(62S~9m
INSERT INTO `coodle`.`users` (`email`, `first_name`, `last_name`, `password`) 
VALUES ('chermworthT@coodle.com', 'Trainer1', 'Trainer1', '$2a$10$CIVe4IO84/GLkDX7thUWrOgYxQn1KZYfBqIULQs0YLGAwqc5oMcj6');

-- Second Trainer Password: !@Hyyr&%)-(9m
-- Second Trainer email: jspankT@coodle.com


-- ------------------------------------------------------
-- CREATE DATABASE `coodle1`;
-- USE `coodle1`;

-- CREATE TABLE `students` (
--     `id` INT,
--     `dateofbirth` DATE,
--     `tuitionfees` DECIMAL(7 , 0 ),
--     PRIMARY KEY (`id`)
-- );

-- CREATE TABLE `trainers` (
--     `id` INT,
--     `subject` VARCHAR(250),
--     PRIMARY KEY (`id`)
-- );

-- CREATE TABLE `users` (
--     `id` INT,
--     `email` VARCHAR(250),
--     `password` VARCHAR(250),
--     `first_name` VARCHAR(250),
--     `last_name` VARCHAR(250),
--     `reset_password_token` VARCHAR(250),
--     FOREIGN KEY (`id`)
--         REFERENCES `students` (id),
--     FOREIGN KEY (`id`)
--         REFERENCES `trainers` (id)
-- );

-- CREATE TABLE `roles` (
--     `id` INT,
--     `role` VARCHAR(250),
--     PRIMARY KEY (`id`)
-- );

-- CREATE TABLE `courses` (
--     `id` INT,
--     `title` VARCHAR(250),
--     `assignment` VARCHAR(250),
--     `price` INT,
--     PRIMARY KEY (`id`)
-- );

-- CREATE TABLE `users_roles` (
--     `id_users` INT NOT NULL,
--     `id_roles` INT NOT NULL,
--     PRIMARY KEY (`id_users` , `id_roles`),
--     FOREIGN KEY (`id_users`)
--         REFERENCES `users` (`id`),
--     FOREIGN KEY (`id_roles`)
--         REFERENCES `roles` (`id`)
-- );

-- CREATE TABLE `studentspercourses` (
--     `id_students` INT NOT NULL,
--     `id_courses` INT NOT NULL,
--     PRIMARY KEY (`id_students` , `id_courses`),
--     FOREIGN KEY (`id_students`)
--         REFERENCES `students` (`id`),
--     FOREIGN KEY (`id_courses`)
--         REFERENCES `courses` (`id`)
-- )