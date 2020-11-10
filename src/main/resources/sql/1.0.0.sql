CREATE TABLE `user`
(
  `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name`    VARCHAR(50),
  `phone_number` VARCHAR(11),
  `password`     VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX (user_name),
  UNIQUE INDEX (phone_number)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `file`
(
  `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(255) NOT NULL,
  `suffix_name`   VARCHAR(10)  NOT NULL,
  `type`          INT          NOT NULL,
  `absolute_path` VARCHAR(255) NOT NULL,
  `user_id`       INT          NOT NULL,
  `size`          INT          NOT NULL,
  `deleted`       TINYINT      NOT NULL DEFAULT 0,
  `file`          TINYINT      NOT NULL DEFAULT 0,
  `create_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`, `suffix_name`, `absolute_path`, `user_id`, file)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
