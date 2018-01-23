DELETE FROM event_counter_audit;
DELETE FROM user_discount_audit;
--
DELETE FROM auditorium_seats;
DELETE FROM event_auditoriums;
DELETE FROM user_lucky_dates;
--
DELETE FROM ticket;
DELETE FROM users;
DELETE FROM event;
DELETE FROM auditorium;
--

--users
-- INSERT INTO users (first_name, last_name, email, birthday) VALUES ('Vladimir0', 'Vys0', 'unlim0@mail.com', TIMESTAMP('1980-01-01 00:00:00'));
-- INSERT INTO users (first_name, last_name, email, birthday) VALUES ('Vladimir1', 'Vys1', 'unlim1@mail.com', TIMESTAMP('1980-01-02 00:00:00'));
--'password' = encoded '$2a$12$KiAaFnYXRWjvp8ScuN2/WuYL.yIwQbSyRg1wFmvAwrhrThzqXFsri'
INSERT INTO users (first_name, last_name, email, birthday, password) VALUES ('Vladimir0', 'Vys0', 'unlim0@mail.com', '1980-01-01 00:00:00', '$2a$12$KiAaFnYXRWjvp8ScuN2/WuYL.yIwQbSyRg1wFmvAwrhrThzqXFsri');
INSERT INTO users (first_name, last_name, email, birthday, password) VALUES ('Vladimir1', 'Vys1', 'unlim1@mail.com', '1980-01-02 00:00:00', '$2a$12$KiAaFnYXRWjvp8ScuN2/WuYL.yIwQbSyRg1wFmvAwrhrThzqXFsri');

--auditoriums
INSERT INTO auditorium (name, number_of_seats) VALUES ('alpha', 10);
INSERT INTO auditorium_seats (auditorium_name, vip_seat) VALUES ('alpha', 1);
INSERT INTO auditorium_seats (auditorium_name, vip_seat) VALUES ('alpha', 2);
INSERT INTO auditorium_seats (auditorium_name, vip_seat) VALUES ('alpha', 3);

INSERT INTO auditorium (name, number_of_seats) VALUES ('beta', 9);
INSERT INTO auditorium_seats (auditorium_name, vip_seat) VALUES ('beta', 5);

INSERT INTO auditorium (name, number_of_seats) VALUES ('gama', 15);
INSERT INTO auditorium_seats (auditorium_name, vip_seat) VALUES ('gama', 1);
INSERT INTO auditorium_seats (auditorium_name, vip_seat) VALUES ('gama', 10);
INSERT INTO auditorium_seats (auditorium_name, vip_seat) VALUES ('gama', 15);

INSERT INTO event (name, base_price, rating) VALUES ('Event0', 10, 'MID');
INSERT INTO event (name, base_price, rating) VALUES ('Event1', 20, 'HIGH');
INSERT INTO event_auditoriums (event_id, auditorium_name, air_date) VALUES (0, 'alpha', '2018-01-01 10:00:00');
INSERT INTO event_auditoriums (event_id, auditorium_name, air_date) VALUES (0, 'alpha', '2018-01-02 12:00:00');
INSERT INTO event_auditoriums (event_id, auditorium_name, air_date) VALUES (1, 'alpha', '2018-02-01 10:00:00');
INSERT INTO event_auditoriums (event_id, auditorium_name, air_date) VALUES (1, 'beta', '2018-02-02 12:00:00');
INSERT INTO event_auditoriums (event_id, auditorium_name, air_date) VALUES (1, 'gama', '2018-02-03 14:00:00');
