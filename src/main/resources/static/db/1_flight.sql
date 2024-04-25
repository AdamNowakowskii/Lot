-- liquibase formatted sql
-- changeset liquibase:1

CREATE TABLE flight
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    flight_number   VARCHAR(50)  NOT NULL UNIQUE,
    route           VARCHAR(500) NOT NULL,
    departure_date  DATE         NOT NULL,
    available_seats INT          NOT NULL
);

INSERT INTO flight (flight_number, route, departure_date, available_seats)
VALUES ('LO123', 'Warsaw,London,Helsinki', CURRENT_TIMESTAMP + 30, 150),
       ('BA456', 'Paris,Amsterdam,Berlin,Prague', CURRENT_TIMESTAMP - 20, 200),
       ('AF789', 'Madrid,Lisbon', CURRENT_TIMESTAMP, 180),
       ('SA012', 'Rome,Vienna,Budapest,Belgrade,Sofia', CURRENT_TIMESTAMP + 100, 220),
       ('TK345', 'Oslo,Stockholm,Copenhagen', CURRENT_TIMESTAMP - 100, 250);