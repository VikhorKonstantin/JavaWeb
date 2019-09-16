CREATE DATABASE `paragliding_db`;

DROP USER IF EXISTS paragliding_app@localhost;

CREATE USER paragliding_app@localhost identified BY 'password';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `paragliding_db`.*
    TO paragliding_app@localhost;