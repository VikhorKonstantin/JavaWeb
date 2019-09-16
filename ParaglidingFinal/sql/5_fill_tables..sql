USE `paragliding_db`;
INSERT INTO `users` (
                     `email`,
                     `password`,
                     `role`)
VALUES (
        "sluga@fai.com",
        "4s8d8s86dg6s8dgs6ddsd",
        2),
       (
        "Jaka@fai.com",
        "sdss7d875ds7d7ds7dsd7",
        2),
       ("Feraric@fai.com",
        "84c8d7da87sa7a7xa87ad",
        2);
INSERT INTO `sportsmen` (user_id,civl_id, surname, name, gender, country, rating, image_path)
VALUES (2,
        8389,
        "Sluga",
        "Matjaz",
        'M',
        "SL",
        353.1,
        ""),
       (3,
        8321,
        "Gorenc",
        "Jaka",
        'M',
        "SL",
        315.8,
        ""),
       (4,
        8388,
        "Matjaz",
        "Feraric",
        'M',
        "SL",
        315.0,
        "");