create sequence location_seq;
create table location
(
    id         bigint primary key not null DEFAULT nextval('location_seq'),
    name       varchar(150),
    street     varchar(150),
    cep        char(8),
    city       varchar(100),
    state      varchar(100),
    created_at timestamp          not null,
    updated_at timestamp
);
ALTER SEQUENCE location_seq
    OWNED BY location.id;

create sequence amenity_seq;
create table amenity
(
    id          bigint primary key not null DEFAULT nextval('amenity_seq'),
    id_location bigint,
    name        varchar(150),
    quantity    int,
    created_at  timestamp          not null,
    updated_at  timestamp,
    constraint fk_location
        foreign key (id_location)
            references location (id)
);
ALTER SEQUENCE amenity_seq
    OWNED BY amenity.id;

create sequence building_seq;
create table building
(
    id          bigint primary key not null DEFAULT nextval('location_seq'),
    id_location bigint,
    name        varchar(100),
    created_at  timestamp          not null,
    updated_at  timestamp,
    constraint fk_location
        foreign key (id_location)
            references location (id)
);
ALTER SEQUENCE building_seq
    OWNED BY building.id;

create sequence room_seq;
create table room
(
    id                bigint primary key not null DEFAULT nextval('location_seq'),
    id_building       bigint,
    type              varchar(100),
    max_capacity      int,
    total_beds        int,
    total_rooms       int,
    total_daily_value decimal(10, 3),
    bathroom_type     varchar(15),
    created_at        timestamp          not null,
    updated_at        timestamp,
    constraint fk_building
        foreign key (id_building)
            references building (id)
);
ALTER SEQUENCE room_seq
    OWNED BY room.id;

create sequence furniture_seq;
create table furniture
(
    id         bigint primary key not null DEFAULT nextval('location_seq'),
    id_room    bigint,
    name       varchar(150),
    quantity   int,
    created_at timestamp          not null,
    updated_at timestamp,
    constraint fk_room
        foreign key (id_room)
            references room (id)
);
ALTER SEQUENCE furniture_seq
    OWNED BY furniture.id;

create sequence bathroom_seq;
create table bathroom
(
    id          bigint primary key not null DEFAULT nextval('bathroom_seq'),
    type        varchar(15),
    description varchar(150),
    created_at  timestamp          not null,
    updated_at  timestamp
);
ALTER SEQUENCE bathroom_seq
    OWNED BY bathroom.id;

create sequence add_on_seq;
create table add_on
(
    id          bigint primary key not null DEFAULT nextval('bathroom_seq'),
    type        varchar(15),
    description varchar(150),
    total_value decimal(10, 3),
    created_at  timestamp          not null,
    updated_at  timestamp
);
ALTER SEQUENCE add_on_seq
    OWNED BY add_on.id;

create sequence client_seq;
create table client
(
    id                   bigint primary key not null DEFAULT nextval('bathroom_seq'),
    cpf                  int                not null,
    passport             int                not null,
    full_name            varchar(150)       not null,
    birthday_date        timestamp          not null,
    nationality          varchar(15),
    home_country_address varchar(150)       not null,
    phone                varchar(30)        not null,
    email                varchar(150)       not null,
    created_at           timestamp          not null,
    updated_at           timestamp
);
ALTER SEQUENCE client_seq
    OWNED BY client.id;

create sequence booking_seq;
create table booking
(
    id         bigint primary key not null DEFAULT nextval('bathroom_seq'),
    id_room    bigint             not null,
    created_at timestamp          not null,
    updated_at timestamp
);
ALTER SEQUENCE booking_seq
    OWNED BY booking.id;

create sequence booking_add_on_seq;
create table booking_add_on
(
    id         bigint primary key not null DEFAULT nextval('bathroom_seq'),
    id_booking bigint             not null,
    id_add_on  bigint             not null,
    created_at timestamp          not null,
    updated_at timestamp,
    constraint fk_booking
        foreign key (id_booking)
            references booking (id),
    constraint fk_add_on
        foreign key (id_add_on)
            references add_on (id)
);
ALTER SEQUENCE booking_add_on_seq
    OWNED BY booking_add_on.id;


insert into bathroom (id, type, description, created_at)
values (1, 'STANDARD', 'Box com Ducha, Privada, Ducha higiênica e pia com espelho', now());
insert into bathroom (id, type, description, created_at)
values (2, 'DELUXE', 'Box com Ducha, Privada, Ducha higiênica e pia dupla com espelho', now());
insert into add_on (id, type, description, total_value, created_at)
values (1, 'SERVICE', 'Jantar', 50.00, now());
insert into add_on (id, type, description, total_value, created_at)
values (2, 'SERVICE', 'Almoço', 50.00, now());
insert into add_on (id, type, description, total_value, created_at)
values (3, 'ITEM', 'Massagem Completa', 80.00, now());
insert into add_on (id, type, description, total_value, created_at)
values (4, 'ITEM', 'Manicure', 80.00, now());

insert into location (id, name, street, cep, city, state, created_at)
values (1, 'Polo 1', 'Rua X', '04296224', 'São Paulo', 'São Paulo', now());
insert into building (id, id_location, name, created_at)
values (1, 1, 'Prédio A', now());
insert into building (id, id_location, name, created_at)
values (2, 1, 'Prédio B', now());
insert into room (id, id_building, type, max_capacity, total_beds, total_rooms, total_daily_value,
                  bathroom_type, created_at)
values (1, 1, 'STANDARD', 2, 2, 5, 100.00, 'STANDARD', now());

insert into amenity (id, id_location, name, quantity, created_at)
values (1, 1, 'Quadra de Futebol', 1, now());
insert into furniture (id, id_room, name, quantity, created_at)
values (1, 1, 'TV de 50 polegdas', 1, now());

SELECT setval('add_on_seq', 4, true);
SELECT setval('bathroom_seq', 2, true);
SELECT setval('location_seq', 1, true);
SELECT setval('building_seq', 2, true);
SELECT setval('room_seq', 1, true);
SELECT setval('amenity_seq', 1, true);
SELECT setval('furniture_seq', 1, true);
