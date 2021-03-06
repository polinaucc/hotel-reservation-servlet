CREATE TABLE public.client
(
    id bigint NOT NULL DEFAULT nextval('client_id_seq'::regclass),
    birthday date,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    middle_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    passport character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_id bigint NOT NULL,
    first_name_uk character varying(255) COLLATE pg_catalog."default",
    middle_name_uk character varying(255) COLLATE pg_catalog."default",
    last_name_uk character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT client_pkey PRIMARY KEY (id),
    CONSTRAINT client_passport_key UNIQUE (passport),
    CONSTRAINT client_user_id_key UNIQUE (user_id),
    CONSTRAINT client_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.client
    OWNER to postgres;


CREATE TABLE public.description
(
    id bigint NOT NULL DEFAULT nextval('description_id_seq'::regclass),
    cost numeric(19,2) NOT NULL,
    count_beds integer NOT NULL,
    count_persons integer NOT NULL,
    room_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT description_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.description
    OWNER to postgres;

CREATE TABLE public.request
(
    id bigint NOT NULL DEFAULT nextval('request_id_seq'::regclass),
    in_date date NOT NULL,
    out_date date NOT NULL,
    status character varying(255) COLLATE pg_catalog."default",
    client_id bigint NOT NULL,
    description_id bigint NOT NULL,
    CONSTRAINT request_pkey PRIMARY KEY (id),
    CONSTRAINT request_client_id_fkey FOREIGN KEY (client_id)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT request_description_id_fkey FOREIGN KEY (description_id)
        REFERENCES public.description (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.request
    OWNER to postgres;

CREATE TABLE public.reservation
(
    id bigint NOT NULL DEFAULT nextval('reservation_id_seq'::regclass),
    sum numeric(19,2) NOT NULL,
    request_id bigint NOT NULL,
    room_id bigint NOT NULL,
    CONSTRAINT reservation_pkey PRIMARY KEY (id),
    CONSTRAINT reservation_room_id_fkey FOREIGN KEY (room_id)
        REFERENCES public.room (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.reservation
    OWNER to postgres;


CREATE TABLE public.room
(
    id bigint NOT NULL DEFAULT nextval('room_id_seq'::regclass),
    room_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description_id bigint NOT NULL,
    CONSTRAINT room_pkey PRIMARY KEY (id),
    CONSTRAINT room_room_number_key UNIQUE (room_number),
    CONSTRAINT room_description_id_fkey FOREIGN KEY (description_id)
        REFERENCES public.description (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.room
    OWNER to postgres;


CREATE TABLE public.user_role
(
    user_id bigint NOT NULL,
    authorities character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.user_role
    OWNER to postgres;

GRANT ALL ON TABLE public.user_role TO postgres WITH GRANT OPTION;

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_username_key UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;



INSERT INTO users (email, username, password )
VALUES ('test1@gmail.com', 'test1', '$2a$10$wZXSm13GbIn9TrWDRSXhZ.QKE/HVAceeJvFMFENzLZHcBp9C2AURC');
INSERT INTO users (email, username, password )
VALUES ('admin@admin', 'admin', '$2a$10$Ku9Sf4YzNXyWykVrh8IYyuQo8XJTOZV48AtIZ0yBF53kMUjBHlJXS');

INSERT INTO user_role (user_id, authorities)
VALUES (1, 'CLIENT');
INSERT INTO user_role (user_id, authorities)
VALUES (2, 'ADMIN');

INSERT INTO client (birthday, first_name, middle_name, last_name, passport, user_id, first_name_uk, middle_name_uk, last_name_uk)
VALUES ('1984-12-10', 'Artem', 'Olehovych', 'Kravchenko', 'BT111111', 1, 'Артем', 'Олегович', 'Кравченко' );

INSERT INTO description (cost, count_beds, count_persons, room_type)
VALUES (1520.00, 2, 3, 'BUSINESS');
INSERT INTO description (cost, count_beds, count_persons, room_type)
VALUES (965.00, 1, 2, 'BALCONY');
INSERT INTO description (cost, count_beds, count_persons, room_type)
VALUES (450.00, 1, 2, 'ECONOMY');

INSERT INTO room (room_number, description_id )
VALUES ('1-01', 2);
INSERT INTO room (room_number, description_id )
VALUES ('1-02', 3);

INSERT INTO request (in_date, out_date, status, client_id, description_id)
VALUES ('2020-05-07', '2020-05-12', 'Accepted', 1,  2);
INSERT INTO request (in_date, out_date, status, client_id, description_id)
VALUES ('2020-05-31', '2020-08-15', 'New_request', 1, 1);
INSERT INTO request (in_date, out_date, status, client_id, description_id)
VALUES ('2020-05-24', '2020-05-25', 'Rejected', 1, 3);


INSERT INTO reservation (sum, request_id, room_id)
VALUES (4825.00, 1, 1);













