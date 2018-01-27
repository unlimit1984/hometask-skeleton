-- DROP TABLE auditorium_seats;
-- DROP TABLE event_auditoriums;
-- DROP TABLE user_lucky_dates;
--
-- DROP TABLE ticket;
-- DROP TABLE users;
-- DROP TABLE event;
-- DROP TABLE auditorium;


CREATE TABLE users (
  id         BIGINT                                 NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 0, INCREMENT BY 1),
  first_name VARCHAR(50)                            NOT NULL,
  last_name  VARCHAR(50)                            NOT NULL,
  email      VARCHAR(50)                            NOT NULL,
  birthday   TIMESTAMP                              NOT NULL,
  password   VARCHAR(60)                            NOT NULL,
  roles      VARCHAR(200) DEFAULT 'REGISTERED_USER' NOT NULL,
  CONSTRAINT primary_key_users PRIMARY KEY (id),
  --UNIQUE(email)
  CONSTRAINT users_email_unique UNIQUE (email)
);

CREATE TABLE user_accounts (
  id      BIGINT           NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 0, INCREMENT BY 1),
  user_id BIGINT REFERENCES users (id),
  money   DOUBLE PRECISION NOT NULL,
  CONSTRAINT primary_key_user_accounts PRIMARY KEY (id)
);

CREATE TABLE event (
  id         BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 0, INCREMENT BY 1),
  name       VARCHAR(50) NOT NULL,
  base_price DOUBLE PRECISION,
  rating     VARCHAR(50) NOT NULL,
  CONSTRAINT primary_key_event PRIMARY KEY (id)
);

CREATE TABLE ticket (
  id        BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 0, INCREMENT BY 1),
  user_id   BIGINT REFERENCES users (id),
  event_id  BIGINT REFERENCES event (id),
  date_time TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  seat      BIGINT NOT NULL,
  CONSTRAINT primary_key_ticket PRIMARY KEY (id)
);

CREATE TABLE auditorium (
  name            VARCHAR(50) NOT NULL PRIMARY KEY,
  number_of_seats BIGINT      NOT NULL
);

--Wiring tables
CREATE TABLE user_lucky_dates (
  user_id        BIGINT REFERENCES users (id),
  lucky_datetime TIMESTAMP NOT NULL,
  CONSTRAINT primary_key_lucky PRIMARY KEY (user_id, lucky_datetime)
);
CREATE TABLE event_auditoriums (
  event_id        BIGINT      NOT NULL REFERENCES event (id),
  auditorium_name VARCHAR(50) NOT NULL REFERENCES auditorium (name),
  air_date        TIMESTAMP   NOT NULL,
  CONSTRAINT primary_key_ea PRIMARY KEY (event_id, auditorium_name, air_date)
);

CREATE TABLE auditorium_seats (
  auditorium_name VARCHAR(50) NOT NULL REFERENCES auditorium (name),
  vip_seat        BIGINT      NOT NULL,
  CONSTRAINT primary_key_as PRIMARY KEY (auditorium_name, vip_seat)
);

--Aspect tables
CREATE TABLE event_counter_audit (
  event_name VARCHAR(50) NOT NULL,
  name       VARCHAR(50) NOT NULL,
  count      INTEGER     NOT NULL,
  CONSTRAINT primary_key_audit_counter PRIMARY KEY (event_name, name)
);

CREATE TABLE user_discount_audit (
  user_id       BIGINT      NOT NULL REFERENCES users (id),
  discount_name VARCHAR(50) NOT NULL,
  count         INTEGER     NOT NULL,
  CONSTRAINT primary_key_user_discount_audit PRIMARY KEY (user_id, discount_name)
);

-- for Remember-Me
CREATE TABLE persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL
);