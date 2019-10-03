CREATE DATABASE `paragliding_test_db`;

DROP USER IF EXISTS paragliding_test_app@localhost;

CREATE USER paragliding_test_app@localhost identified BY 'test_password';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `paragliding_test_db`.*
    TO paragliding_test_app@localhost;