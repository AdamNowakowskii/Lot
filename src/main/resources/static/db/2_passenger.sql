-- liquibase formatted sql
-- changeset liquibase:2

CREATE TABLE passenger
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100) NOT NULL,
    phone_number VARCHAR(18)  NOT NULL
);

INSERT INTO passenger (first_name, last_name, phone_number)
VALUES ('Adam', 'Zieliński', '+48 111222333'),
       ('Magdalena', 'Kaczmarek', '+48 444555666'),
       ('Stefan', 'Nowakowski', '+48 231321345'),
       ('Krzysztof', 'Wojciechowski', '+48 777888999'),
       ('Barbara', 'Kowalczyk', '+48 111222444'),
       ('Marek', 'Kowal', '+48 333444555'),
       ('Aleksandra', 'Jankowska', '+48666777888'),
       ('Tomasz', 'Michalski', '+48 999888777'),
       ('Ewa', 'Zawadzka', '+48 222333444'),
       ('Łukasz', 'Dąbrowski', '+48 555666777'),
       ('Karolina', 'Piotrowska', '+48 888999111'),
       ('Robert', 'Szymański', '+48 123456789'),
       ('Natalia', 'Woźniak', '+48 987654322'),
       ('Mariusz', 'Kozłowski', '+48 987654321'),
       ('Agnieszka', 'Grabowska', '+48 111222333'),
       ('Michał', 'Pawlak', '+48 444555666'),
       ('Iwona', 'Dudek', '+48 777888999'),
       ('Radosław', 'Adamczyk', '+48 111222444'),
       ('Monika', 'Zielińska', '+48 333444555'),
       ('Artur', 'Sikora', '+48 666777888'),
       ('Patrycja', 'Walczak', '+48 999888777'),
       ('Mariusz', 'Duda', '+48 222333444'),
       ('Aneta', 'Kurek', '+48 555666777'),
       ('Marcin', 'Stępień', '+48 888999111'),
       ('Dorota', 'Marciniak', '+48 123 456 789'),
       ('Kamil', 'Krawczyk', '+48 987 654 322'),
       ('Sylwia', 'Olejnik', '+48 987 654 321'),
       ('Bartosz', 'Pająk', '+48 111 222 333'),
       ('Paulina', 'Rutkowska', '+48 444 555 666'),
       ('Grzegorz', 'Zięba', '+48 777 888 999'),
       ('Joanna', 'Szczepańska', '+48 111 222 444');