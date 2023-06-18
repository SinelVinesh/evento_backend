CREATE USER evento WITH PASSWORD 'evento';
CREATE DATABASE evento;
ALTER DATABASE evento OWNER TO evento;

CREATE TABLE "token"
(
    "id"      serial PRIMARY KEY,
    "user_id" integer      NOT NULL,
    "token"   varchar(255) NOT NULL
);

CREATE TABLE "user_type"
(
    "id"   serial PRIMARY KEY,
    "name" varchar(255) NOT NULL
);

CREATE TABLE "user"
(
    "id"           serial PRIMARY KEY,
    "username"     varchar(255) NOT NULL,
    "email"        varchar(255) NOT NULL,
    "password"     varchar(255) NOT NULL,
    "user_type_id" integer      NOT NULL
);

CREATE TABLE "event_type"
(
    "id"   serial PRIMARY KEY,
    "name" varchar(255) NOT NULL
);

CREATE TABLE "location"
(
    "id"           serial PRIMARY KEY,
    "name"         varchar(255)   NOT NULL,
    "max_capacity" integer        NOT NULL,
    "rent_price"   numeric(15, 2) NOT NULL
);

CREATE TABLE "event"
(
    "id"            serial PRIMARY KEY,
    "name"          varchar(255) NOT NULL,
    "event_type_id" integer      NOT NULL,
    "location_id"   integer      NOT NULL,
    "date"          timestamp    NOT NULL,
    "tour_id"       integer
);

CREATE TABLE "event_seat_category"
(
    "id"           serial PRIMARY KEY,
    "event_id"     integer        NOT NULL,
    "name"         varchar(255)   NOT NULL,
    "max_capacity" integer        NOT NULL,
    "price"        numeric(15, 2) NOT NULL
);

CREATE TABLE "material"
(
    "id"         serial PRIMARY KEY,
    "name"       varchar(255)   NOT NULL,
    "rent_price" numeric(15, 2) NOT NULL
);

CREATE TABLE "event_material"
(
    "id"          serial PRIMARY KEY,
    "event_id"    integer NOT NULL,
    "material_id" integer NOT NULL
);

CREATE TABLE "ticket_sale"
(
    "id"                     serial PRIMARY KEY,
    "event_seat_category_id" integer      NOT NULL,
    "client_name"            varchar(255) NOT NULL,
    "date"                   timestamp    NOT NULL,
    "amount"                 integer      NOT NULL
);

CREATE TABLE "tour"
(
    "id"         serial PRIMARY KEY,
    "name"       varchar(255) NOT NULL,
    "start_date" date         NOT NULL,
    "end_date"   date         NOT NULL
);

ALTER TABLE app_user
    ADD FOREIGN KEY ("user_type_id") REFERENCES "user_type" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("event_type_id") REFERENCES "event_type" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("location_id") REFERENCES "location" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("tour_id") REFERENCES "tour" ("id");

ALTER TABLE "event_seat_category"
    ADD FOREIGN KEY ("event_id") REFERENCES "event" ("id");

ALTER TABLE "event_material"
    ADD FOREIGN KEY ("event_id") REFERENCES "event" ("id");

ALTER TABLE "event_material"
    ADD FOREIGN KEY ("material_id") REFERENCES "material" ("id");

ALTER TABLE "ticket_sale"
    ADD FOREIGN KEY ("event_seat_category_id") REFERENCES "event_seat_category" ("id");
ALTER TABLE "token"
    ADD FOREIGN KEY (app_user_id) REFERENCES app_user ("id");