DROP TABLE IF EXISTS `livefile`;

CREATE TABLE IF NOT EXISTS `livefile` (
    `record_id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(500),
    `content` longtext,
    `label` VARCHAR(500)
);
