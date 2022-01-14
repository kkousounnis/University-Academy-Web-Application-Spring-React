-- First create databse then run project with spring boot,
-- the database will be created automatically with ORM technology
CREATE DATABASE IF NOT EXISTS `coodle`;

-- then insert the roles
USE `coodle`;

INSERT INTO roles(role) VALUES('ROLE_USER');
INSERT INTO roles(role) VALUES('ROLE_MODERATOR');
INSERT INTO roles(role) VALUES('ROLE_ADMIN');