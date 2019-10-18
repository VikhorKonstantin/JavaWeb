USE `paragliding_test_db`;

INSERT INTO `users` (`id`,
                     `email`,
                     `password`,
                     `role`)
VALUES (1,
        "admin@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$KObIGfKNbvxIa0KlWotAqQ$0QExN1Z4anl+q81Eqx0EVw",
        0);

INSERT INTO `disciplines` (`id`, `name`)
VALUES (1, "PG_AC"),
       (2, "PG"),
       (3, "PG_AER_SOLO"),
       (4, "PG_AER_SYNCRO"),
       (5, "HG_C1"),
       (6, "HG_C2");
