-- liquibase formatted sql
-- changeset liquibase:3

CREATE TABLE flight_passenger
(
    flight_id    BIGINT NOT NULL,
    passenger_id BIGINT NOT NULL,
    PRIMARY KEY (flight_id, passenger_id),
    FOREIGN KEY (flight_id) REFERENCES flight (id),
    FOREIGN KEY (passenger_id) REFERENCES passenger (id)
);

INSERT INTO flight_passenger (flight_id, passenger_id)
VALUES (2, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (2, 11),
       (2, 12),

       (3, 13),
       (3, 14),
       (3, 15),
       (3, 16),
       (3, 17),
       (3, 18),

       (4, 19),
       (4, 20),
       (4, 21),
       (4, 22),
       (4, 23),
       (4, 24),

       (5, 25),
       (5, 26),
       (5, 27),
       (5, 28),
       (5, 29),
       (5, 30);