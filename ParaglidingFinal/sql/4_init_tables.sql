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
VALUES (1, "Paragliding Accuracy"),
       (2, "Paragliding"),
       (3, "Paragliding Aerobatic Solo"),
       (4, "Paragliding Aerobatic Syncro"),
       (5, "Hang Gliding Class 1"),
       (6, "Hang Gliding Class 2")