CREATE TABLE `Users`(
    `u_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Users` ADD PRIMARY KEY `users_u_id_primary`(`u_id`);
CREATE TABLE `albums`(
    `albumid` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `album_name` TEXT NOT NULL
);
ALTER TABLE
    `albums` ADD PRIMARY KEY `albums_albumid_primary`(`albumid`);
CREATE TABLE `songs`(
    `songid` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title_of_song` VARCHAR(255) NOT NULL,
    `artist` VARCHAR(255) NOT NULL,
    `genre` VARCHAR(255) NOT NULL,
    `duration` TIME NOT NULL,
    `albumid` INT NOT NULL
);
ALTER TABLE
    `songs` ADD PRIMARY KEY `songs_songid_primary`(`songid`);
CREATE TABLE `podcast`(
    `pid` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `podcast_name` VARCHAR(255) NOT NULL,
    `celebrity_name` VARCHAR(255) NOT NULL,
    `description` TEXT NOT NULL,
    `duration` TIME NOT NULL,
    `publisherdate` DATE NOT NULL
);
ALTER TABLE
    `podcast` ADD PRIMARY KEY `podcast_pid_primary`(`pid`);
CREATE TABLE `playlist`(
    `playlist_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `playlist_name` VARCHAR(255) NOT NULL,
    `track_type` VARCHAR(255) NOT NULL,
    `track_name` VARCHAR(255) NOT NULL,
    `sid` INT NOT NULL,
    `pid` INT NOT NULL AUTO_INCREMENT
);
ALTER TABLE
    `playlist` ADD PRIMARY KEY `playlist_playlist_id_primary`(`playlist_id`);
ALTER TABLE
    `songs` ADD CONSTRAINT `songs_albumid_foreign` FOREIGN KEY(`albumid`) REFERENCES `albums`(`albumid`);
ALTER TABLE
    `playlist` ADD CONSTRAINT `playlist_sid_foreign` FOREIGN KEY(`sid`) REFERENCES `songs`(`songid`);
ALTER TABLE
    `playlist` ADD CONSTRAINT `playlist_pid_foreign` FOREIGN KEY(`pid`) REFERENCES `podcast`(`pid`);