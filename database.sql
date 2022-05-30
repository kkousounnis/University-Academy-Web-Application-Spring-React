-- First create databse then run project with spring boot,
-- the database will be created automatically with ORM technology
CREATE DATABASE IF NOT EXISTS `coodle`;

-- then insert the roles
USE `coodle`;

INSERT INTO `roles`(`role`) VALUES('ROLE_USER');
INSERT INTO `roles`(`role`) VALUES('ROLE_MODERATOR');
INSERT INTO `roles`(`role`) VALUES('ROLE_ADMIN');


INSERT INTO `courses`(`semester`,`title`) VALUES('ALGORITHMS','A');
INSERT INTO `courses`(`semester`,`title`) VALUES('ANALYSIS','A');
INSERT INTO `courses`(`semester`,`title`) VALUES('PYTHON','B');
INSERT INTO `courses`(`semester`,`title`) VALUES('DATABASES','B');
INSERT INTO `courses`(`semester`,`title`) VALUES('MASTER_THESIS','B');

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