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
        "SI",
        353.1,
        ""),
       (3,
        8321,
        "Gorenc",
        "Jaka",
        'M',
        "SI",
        315.8,
        ""),
       (4,
        8388,
        "Matjaz",
        "Feraric",
        'M',
        "SI",
        315.0,
        "");
INSERT INTO `competitions`(`id`,`name`,date, discipline_id,description, status, participation_fee)
VALUES (1, "6th FAI European Paragliding Accuracy Championship",20180916, 1, "", "FINISHED", 20 ),
       (2, "PGA Czech Open 2019 IPGA European CUP",20190816, 1, "", "FINISHED", 15),
       (3, "Albania Open", 20190508 , 1 , "", "FINISHED", 20),
       (4, "Paragliding Accuracy European Cup, Paragliding Accuracy Balkanic Cup, Campionatul National al Romani",
        20190607 ,1, "", "FINISHED", 20);