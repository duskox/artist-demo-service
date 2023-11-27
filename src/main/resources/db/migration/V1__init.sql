CREATE TABLE `artists` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `tracks` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`artist_id` INTEGER NOT NULL,
`genre` VARCHAR(255),
`length_in_seconds` int,
PRIMARY KEY (`id`),
FOREIGN KEY (`artist_id`) REFERENCES `artists`(`id`)
);

CREATE TABLE `artist_aliases` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`artist_id` INTEGER NOT NULL,
`alias` VARCHAR(255) NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`artist_id`) REFERENCES `artists`(`id`)
);
