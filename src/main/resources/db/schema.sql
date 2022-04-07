CREATE TABLE `meta` (
    `id` varchar(255) NOT NULL,
    `label_id` varchar(255) DEFAULT NULL,
    `height` int DEFAULT NULL,
    `width` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_loka3qlojtnkyyrnpwighc94i` (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `project_info` (
    `class_id` varchar(255) NOT NULL,
    `class_name` varchar(255) DEFAULT NULL,
    `super_category` varchar(255) DEFAULT NULL,
    `annotation_type` varchar(20) DEFAULT NULL,
    `property_name` varchar(255) DEFAULT NULL,
    `property_unit` varchar(5) DEFAULT NULL,
    PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `object` (
    `id` varchar(255) NOT NULL,
    `points` varchar(20000) DEFAULT NULL,
    `property_value` int DEFAULT NULL,
    `meta_id` varchar(255) DEFAULT NULL,
    `class_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK81hlli3q2lc419a5qe0h6jx1b` (`meta_id`),
    KEY `FKa3u8yag4lm6r253n04xe055ts` (`class_id`),
    CONSTRAINT `FK81hlli3q2lc419a5qe0h6jx1b` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`id`),
    CONSTRAINT `FKa3u8yag4lm6r253n04xe055ts` FOREIGN KEY (`class_id`) REFERENCES `project_info` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;