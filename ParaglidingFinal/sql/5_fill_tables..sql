USE `paragliding_db`;
INSERT INTO `users` (`email`,
                     `password`,
                     `role`)
VALUES ("sluga@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$a2oXAg1a8G4T7EzDMMcxMQ$igYq22CEPy8cJMfGg3IN5g",
        2),
       ("Jaka@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$FrEqAJBf/R1x9FiAVl3IEQ$65flTRAt5ZhjS8GKOpOKUA",
        2),
       ("Feraric@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$c0uBUZsR8qDpDTf5i95kdg$QPct/VvmF57W2mkUA2kV1A",
        2),
       ("Vikhor@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$cehx9+2kz8prK8zbw2UmEw$Snb0yXwtOjvvg8K8hRmjlw",
        2),
       ("Ivanov@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$fqHucd54VlT7jvRD48+0oQ$fsv6VmMuCqcpjIoCohVuZg",
        2),
       ("Kokkotou@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$PMo7672l3EtT5v6NJ8HdOg$D7D5hmXF1+a7MqGcH8Dm2Q",
        2),
       ("Alleva@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$WsOV7PjOkdHKB2rhpf8Xzw$HJKUDPD+tfV987y7A2wDIQ",
        2),
       ("Manese@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$iapB0cep8B/zM6haF9N3WA$OLmUj2NyTerHBNZFr6HIKQ",
        2),
       ("Reiling@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$W0nouLml3OHKJb5zV0mU8g$2r4MHqiDvpKeI/ovDsWX/g",
        2),
       ("Hearst@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$5rP7iHL1cnpIbjZBlyot9g$eCefcDcnET3Yr9REWUIdCw",
        2),
       ("Stephanian@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$impRjJvUxRK8G/O8LfqCsg$R9FyETdsgoLGlv/XBcSFvg",
        2),
       ("Pupkin@fai.com",
        "$argon2i$v=19$m=65536,t=5,p=1$itZAP+ijAptT1/gdy2uSmQ$U7E/ZbrMK+mdRt6nJTsHnA",
        2);
INSERT INTO `sportsmen` (user_id, civl_id, name, surname, gender, country, rating)
VALUES (2,
        8389,
        "Sluga",
        "Matjaz",
        'M',
        "SI",
        353.1),
       (3,
        8321,
        "Gorenc",
        "Jaka",
        'M',
        "SI",
        315.8),
       (4,
        8388,
        "Feraric",
        "Matjaz",
        'M',
        "SI",
        315.0),
       (5,
        7855,
        "Yauheni",
        "Vikhor",
        'M',
        "BY",
        310.0),
       (6,
        8782,
        "John",
        "Ivanov",
        'M',
        "PL",
        300.0),
       (7,
        8792,
        "Tim",
        "Kokkotou",
        'M',
        "UK",
        280.0),
       (8,
        7895,
        "Kate",
        "Alleva",
        'F',
        "UK",
        285.0),
       (9,
        8981,
        "Ann",
        "Manese",
        'F',
        "LT",
        285.0),
       (10,
        7871,
        "Maxim",
        "Reiling",
        'M',
        "LT",
        295.0),
       (11,
        9892,
        "Oleg",
        "Hearst",
        'M',
        "LT",
        295.0),
       (12,
        1025,
        "Ivan",
        "Stephanian",
        'M',
        "RU",
        295.0),
       (13,
        5987,
        "Ivan",
        "Pupkin",
        'M',
        "RU",
        299.0);
INSERT INTO `competitions`(id, organizer_id, name, date, discipline_id, status, description, participation_fee)
VALUES (1, 1, "6th FAI European Paragliding Accuracy Championship", "2018-09-16", 1, 4, "Descripion template..1", 20),
       (2, 1, "PGA Czech Open 2019 IPGA European CUP", "2019-08-16", 1, 4, "Descripion template.2.", 15),
       (3, 1, "Albania Open", "2019-05-08", 1, 4, "Descripion template..3", 20),
       (4, 1, "Paragliding Accuracy European Cup, Paragliding Accuracy Balkanic Cup, Campionatul National al Romani",
        "2019-06-07", 1, 4, "Descripion template..4", 20),
       (5, 1, "Polessky Dessant", "2019-12-12", 1, 1, "Mozyr moved competition:)", 5.5),
       (6, 1, "Open BSU paragliding Championship ", "2020-12-12", 1, 1, "BSU moved championship", 0.0);
INSERT INTO `applications` (sportsman_id, competition_id)
VALUES (8389, 1),
       (8389, 3),
       (8388, 2),
       (8388, 1),
       (8321, 2),
       (8321, 4),
       (8389, 4),
       (8388, 3),
       (8388, 5),
       (7855, 5),
       (5987, 5),
       (8388, 6),
       (7855, 6),
       (5987, 6);
INSERT INTO `results` (score, sportsman_id, competition_id)
VALUES (13, 8389, 1),
       (12, 8388, 1),
       (250, 8388, 2),
       (12, 8321, 2),
       (5, 8389, 3),
       (19, 8388, 3),
       (7, 8321, 4),
       (5, 8389, 4);