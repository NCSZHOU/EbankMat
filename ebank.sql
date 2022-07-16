CREATE DATABASE IF NOT EXISTS `cqjdudb` ;
USE `cqjdudb`;

CREATE TABLE IF NOT EXISTS `role` (
        `id` int NOT NULL,
        `rolename` varchar(50) NOT NULL DEFAULT 'customer'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `role` (`id`, `rolename`) VALUES (1, 'customer');

CREATE TABLE IF NOT EXISTS `user` (
    `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `password` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `enabled` int DEFAULT NULL,
    `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`id`),
    KEY `userName` (`user_name`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

INSERT INTO `user` (`id`, `user_name`, `password`, `enabled`,`account`) VALUES
    ('1', 'Greyson', '123456', 1,'622034845');

CREATE TABLE IF NOT EXISTS `user_role` (
    `id` int NOT NULL,
    `roleid` int NOT NULL,
     `userid` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user_role` (`id`, `roleid`, `userid`) VALUES (1, 1, 1);
