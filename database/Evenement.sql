CREATE USER evento WITH PASSWORD 'evento';
CREATE DATABASE evento;
ALTER DATABASE evento OWNER TO evento;

CREATE TABLE "token"
(
    "id"          serial PRIMARY KEY,
    "app_user_id" integer      NOT NULL,
    "token"       varchar(255) NOT NULL
);

CREATE TABLE "user_type"
(
    "id"   serial PRIMARY KEY,
    "name" varchar(255) NOT NULL
);

CREATE TABLE "app_user"
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

CREATE TABLE "location_type"
(
    "id"   serial PRIMARY KEY,
    "name" varchar(255) NOT NULL
);

CREATE TABLE "location"
(
    "id"            serial PRIMARY KEY,
    "name"          varchar(255)   NOT NULL,
    "max_capacity"  integer        NOT NULL,
    "location_type" integer,
    "rent_price"    numeric(15, 2) NOT NULL
);

CREATE TABLE "event_status"
(
    "id"   serial PRIMARY KEY,
    "name" varchar(255) NOT NULL
);

CREATE TABLE "event"
(
    "id"                         serial PRIMARY KEY,
    "name"                       varchar(255) NOT NULL,
    "event_type_id"              integer      NOT NULL,
    "location_id"                integer      NOT NULL,
    "sound_system_id"            integer      NOT NULL,
    "sound_system_hour_duration" integer      NOT NULL,
    "logistics_id"               integer      NOT NULL,
    "start_date"                 timestamp    NOT NULL,
    "end_date"                   timestamp    NOT NULL,
    "tour_id"                    integer,
    "deleted"                    boolean      NOT NULL,
    "event_status_id"            integer      NOT NULL
);

CREATE TABLE "event_artist"
(
    "id"            serial PRIMARY KEY,
    "event_id"      integer NOT NULL,
    "artist_id"     integer NOT NULL,
    "hour_duration" integer NOT NULL
);

CREATE TABLE "event_expenses"
(
    "id"              serial PRIMARY KEY,
    "expense_type_id" integer        NOT NULL,
    "amount"          numeric(15, 2) NOT NULL
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

CREATE TABLE "artist"
(
    "id"          serial PRIMARY KEY,
    "name"        varchar(255)   NOT NULL,
    "hourly_rate" numeric(15, 2) NOT NULL
);

CREATE TABLE "sound_system"
(
    "id"          serial PRIMARY KEY,
    "name"        varchar(255)   NOT NULL,
    "hourly_rate" numeric(15, 2) NOT NULL
);

CREATE TABLE "logistics"
(
    "id"         serial PRIMARY KEY,
    "name"       varchar(255)   NOT NULL,
    "daily_rate" numeric(15, 2) NOT NULL
);

CREATE TABLE "expense_type"
(
    "id"   serial PRIMARY KEY,
    "name" varchar(255) NOT NULL
);

ALTER TABLE "token"
    ADD FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id");

ALTER TABLE "app_user"
    ADD FOREIGN KEY ("user_type_id") REFERENCES "user_type" ("id");

ALTER TABLE "location"
    ADD FOREIGN KEY ("location_type") REFERENCES "location_type" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("event_type_id") REFERENCES "event_type" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("location_id") REFERENCES "location" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("sound_system_id") REFERENCES "sound_system" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("logistics_id") REFERENCES "logistics" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("tour_id") REFERENCES "tour" ("id");

ALTER TABLE "event"
    ADD FOREIGN KEY ("event_status_id") REFERENCES "event_status" ("id");

ALTER TABLE "event_artist"
    ADD FOREIGN KEY ("event_id") REFERENCES "event" ("id");

ALTER TABLE "event_artist"
    ADD FOREIGN KEY ("artist_id") REFERENCES "artist" ("id");

ALTER TABLE "event_expenses"
    ADD FOREIGN KEY ("expense_type_id") REFERENCES "expense_type" ("id");

ALTER TABLE "event_seat_category"
    ADD FOREIGN KEY ("event_id") REFERENCES "event" ("id");

ALTER TABLE "event_material"
    ADD FOREIGN KEY ("event_id") REFERENCES "event" ("id");

ALTER TABLE "event_material"
    ADD FOREIGN KEY ("material_id") REFERENCES "material" ("id");

ALTER TABLE "ticket_sale"
    ADD FOREIGN KEY ("event_seat_category_id") REFERENCES "event_seat_category" ("id");
