USE paragliding_db;

INSERT INTO `users` (`id`,
                     `email`,
                     `password`,
                     `role`)
VALUES (1,
        "admin@fai.com",
        "21232F297A57A5A743894A0E4A801FC3",
        0);

INSERT INTO `disciplines` (`id`, `name`)
VALUES (1, "PG_AC"),
       (2, "PG"),
       (3, "PG_AER_SOLO"),
       (4, "PG_AER_SYNCRO"),
       (5, "HG_C1"),
       (6, "HG_C2");
