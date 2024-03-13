create sequence location_seq;
create table location
(
    id         bigint primary key not null DEFAULT nextval('location_seq'),
    name       varchar(150),
    street     varchar(150),
    cep        int,
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
ALTER SEQUENCE building_seq
    OWNED BY building.id;

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
    birthday_date        datetime           not null,
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
            references booking (id)
);
ALTER SEQUENCE booking_add_on_seq
    OWNED BY booking_add_on.id;
