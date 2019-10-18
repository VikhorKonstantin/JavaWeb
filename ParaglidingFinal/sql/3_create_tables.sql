USE `paragliding_db`;

CREATE TABLE `users` (
         `id` INT NOT NULL AUTO_INCREMENT,
         `email` VARCHAR(255) NOT NULL UNIQUE,
         `password` CHAR(75) NOT NULL,
         `role` TINYINT NOT NULL,
         CONSTRAINT PK_users  PRIMARY KEY (`id`),
         CONSTRAINT CH_users CHECK (`role` IN (0, 1, 2))
);

CREATE TABLE `sportsmen` (
         -- FAI id
        `civl_id` INT NOT NULL UNIQUE,
        `user_id` INT NOT NULL UNIQUE,
        `surname` VARCHAR(255) NOT NULL,
        `name` VARCHAR(255) NOT NULL,
         --  M F
        `gender` char(1) NOT NULL ,
        `country` VARCHAR(5) NOT NULL,
        `rating` FLOAT(4,1),
        `image_path` VARCHAR(4096),
         CONSTRAINT PK_sportsmen
                PRIMARY KEY (`civl_id`),
         CONSTRAINT FK_sportsmen_users
                         FOREIGN KEY (`user_id`) REFERENCES users(`id`),
         CONSTRAINT CH_sportsmen CHECK ( `rating` >= 0  )
);

CREATE INDEX IDX_sportsmen_country
   ON `sportsmen`(`country`);
CREATE INDEX IDX_sportsmen_rating
   ON `sportsmen`(`rating`);


CREATE TABLE `disciplines` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    CONSTRAINT PK_disciplines
               PRIMARY KEY (`id`)
);


CREATE TABLE `competitions` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `organizer_id` INT NOT NULL,
    `date` DATE NOT NULL,
    `name` VARCHAR(256) NOT NULL,
    `discipline_id` INT NOT NULL,
    `status` TINYINT NOT NULL,
    `participation_fee` FLOAT NOT NULL,
    `description` TEXT,
    CONSTRAINT PK_competitions
       PRIMARY KEY (`id`),
    CONSTRAINT FK_competitions_disciplines
            FOREIGN KEY (`discipline_id`) REFERENCES `disciplines`(`id`),
    CONSTRAINT FK_competitions_users
            FOREIGN KEY (`organizer_id`) REFERENCES `users`(`id`),
    CONSTRAINT CH_competitions_status CHECK (`status` BETWEEN 0 AND 4),
    CONSTRAINT CH_competitions_participation_fee CHECK ( `participation_fee` >= 0 )
);


CREATE TABLE `results` (
    `score` INT NOT NULL,
    `sportsman_id` INT NOT NULL,
    `competition_id` INT NOT NULL,
    CONSTRAINT PK_results
        PRIMARY KEY(`competition_id`, `sportsman_id`),
    CONSTRAINT FK_results_sportsmen
        FOREIGN KEY (`sportsman_id`) REFERENCES sportsmen(civl_id) ON DELETE CASCADE,
    CONSTRAINT FK_results_competitions
        FOREIGN KEY (`competition_id`) REFERENCES competitions(id) ON DELETE CASCADE,
    CONSTRAINT CH_results CHECK ( `score` >= 0 )
);

CREATE INDEX IDX_results
    ON `results`(`score`);

/**
  Заявки на участие
  Связывает предстощие соревнования со спортсменами, которые кинули заявки.
 */
CREATE TABLE `applications` (
     `sportsman_id` INT NOT NULL,
     `competition_id` INT NOT NULL,
     CONSTRAINT PK_applications
                PRIMARY KEY(`competition_id`, `sportsman_id`),
     CONSTRAINT FK_applications_sportsmen
                FOREIGN KEY (`sportsman_id`) REFERENCES sportsmen(civl_id) ON DELETE CASCADE,
     CONSTRAINT FK_applications_competitions
                FOREIGN KEY (`competition_id`) REFERENCES competitions(id) ON DELETE CASCADE
);



