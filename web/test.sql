--select * from user;
-- update user SET id_role = 1, discount = 0, balance = 0 WHERE id_user=1;

-- -- create table direction (
-- --     id_dir int not null auto_increment,
-- --     dir_name varchar(20),
-- --     primary key (id_dir),
-- --     unique(id_dir)
-- -- );
-- 
-- -- insert into direction values (1, "name1"), (2, "name2"), (3, "name3");
-- 
-- -- select * from direction;
-- 
-- -- create table countryes (
-- --     id_count int not null auto_increment,
-- --     count_name varchar(20),
-- --     primary key (id_count),
-- --     unique(id_count)
-- -- );
-- -- 
-- -- create table direction_countrys (
-- --     id_dir int not null,
-- --     id_count int not null,
-- --     constraint fk_dir_count_id_dir foreign key (id_dir) references direction(id_dir),
-- --     constraint fk_dir_count_id_count foreign key (id_count) references countryes(id_count)
-- -- );
-- 
-- -- insert into countryes values (1, "polsk"), (2, "litva"), (3, "germany"), (4, "frans");
-- 
-- -- insert into direction_countrys values (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 4);
-- 
-- -- select d.dir_name, c.count_name from direction d
-- --     inner join direction_countrys dc on (dc.id_dir = d.id_dir)
-- --     inner join countryes c on (dc.id_count = c.id_count and c.count_name = "litva");
-- --     where c.count_name = "litva";
-- 
-- -- select * from peoples;
-- -- insert into peoples(f_fio) values ('Grouk');
-- 
-- -- alter table peoples modify column id int not null auto_increment;
-- 
-- -- CREATE TABLE role (
-- --     id_role              int NOT NULL auto_increment,
-- --     role_name            varchar(20) NULL,
-- --     primary key (id_role),
-- --     unique(id_role)
-- -- );
-- -- 
-- -- insert into role(role_name) values ('USER'), ('ADMIN'), ('GUEST');
-- -- 
-- -- CREATE TABLE user (
-- --     id_user              int NOT NULL auto_increment,
-- --     id_role              int default 3,
-- --     login                varchar(20) NOT NULL,
-- --     password             int NOT NULL,
-- --     email                varchar(20) NOT NULL,
-- --     phone                varchar(20) NOT NULL,
-- --     discount             int default 0,
-- --     balance              float default 0,
-- --     lang                 varchar(2),
-- --     status               TINYINT default 1,
-- --     primary key (id_user),
-- --     unique(id_user),
-- --     unique(email),
-- --     unique(login),
-- --     constraint fk_user_role_id foreign key (id_role) references role(id_role)
-- -- );
-- 
-- select * from user;
-- -- select * from role;
-- 
-- -- alter table user modify column email varchar(60) not null;

-- CREATE TABLE country (
--     id_country          int NOT NULL auto_increment,
--     name                varchar(40) NOT NULL,
--     status              TINYINT default 1,
--     picture             varchar(60) not null,
--     primary key(id_country),
--     unique(id_country)
-- 
-- );

--alter table country add column id_text int default null;
-- alter table country drop column id_text;


-- CREATE TABLE description (
--     id_description int not null auto_increment,
--     text LONGTEXT,
--     primary key (id_description),
--     unique(id_description)
-- );

-- alter table country add column id_description int not null;
-- alter table country add constraint fk_country_description_id foreign key (id_description) references description(id_description);

-- CREATE TABLE city (
--        id_city              int NOT NULL auto_increment,
--        id_country           int NOT NULL,
--        name                 varchar(40) not null,
--        status               TINYINT default 1,
--        picture              varchar(60) not null,
--        id_description       int not null,
--        primary key (id_city),
--        unique(id_city),
--        constraint fk_city_country_id foreign key (id_country) references country(id_country),
--        constraint fk_city_description_id foreign key (id_description) references description(id_description)
-- );

--alter table hotel modify column id_description int default null;

-- CREATE TABLE hotel (
--        id_hotel             int NOT NULL auto_increment,
--        id_city              int NOT NULL,
--        name                 varchar(40) NOT NULL,
--        stars                int default 1,
--        status               TINYINT default 1,
--        picture              varchar(60) not null,
--        id_description       int not null,
--        primary key (id_hotel),
--        unique (id_hotel),
--        constraint fk_hotel_city_id foreign key (id_city) references city(id_city),
--        constraint fk_hotel_description_id foreign key (id_description) references description(id_description)
-- )




-- insert into description(text) values 
-- ('<h>ITALY</h><p>Italy, one of the most romantic and most visited countries in the world. Every corner of this country - is unique and unique. </p>
-- <p>Italy - the country that have nothing to compare - as she is beautiful and majestic. Fragile, delicate from the endless bridges and canals of Venice. Center of the universe "caput mundi" - the great Rome. Cultural and artistic heart of the world since the Renaissance - Florence proud. And how many of them still, small historic towns of Italy, sealed for centuries by artists and poets, perennial caches past and present! This country can not be used, and every building here, each bend of the street - a work of art of the Supreme Creator.</p>
-- <p>Italy is a country of miracles. You have a great opportunity to visit this beautiful fairy tale, to plunge into the unknown beauty, ride a gondola under the starry sky of Venice, wander through the ancient streets of the world, to go to eat in a small cafe, which can be found on every corner.</p>');

--select * from description;

--  insert into country (name, picture, id_description) values ('Italy', '/images/italy.jpg', 1);

-- select * from peoples;
-- insert into peoples(f_fio) values ('ttt');
--SELECT * from peoples where id = LAST_INSERT_ID();

-- CREATE TABLE tour_type (
--     id_tour_type     int not null auto_increment,
--     name_tour_type   varchar(60) not null,
--     primary key (id_tour_type),
--     unique (id_tour_type)
-- );

-- insert into tour_type(name_tour_type) values ("excursion tour"), 
--                                             ("recreation tour "),
--                                             ("weekend tour"),
--                                             ("shopping tour"),
--                                             ("ski tour");

-- CREATE TABLE transportation_mode (
--     id_mode     int not null auto_increment,
--     name_mode   varchar(60) not null,
--     primary key (id_mode),
--     unique (id_mode)
-- );

--insert into transportation_mode(name_mode) values ("air"), ("bus"), ("train"), ("ship");


-- CREATE TABLE direction (
--         id_direction         int NOT NULL auto_increment,
--         id_tour_type        int not null,
--         id_mode             int not null,
--         id_description       int not null,
--         name                 varchar(80) NOT NULL,
--         picture             varchar(60) not null,
--         text                 varchar(20) NULL,
--         status               TINYINT default 1,
--         primary key (id_direction),
--         unique (id_direction),
--         constraint fk_direction_type_id foreign key (id_tour_type) references tour_type(id_tour_type),
--         constraint fk_direction_mode_id foreign key (id_mode) references transportation_mode(id_mode),
--         constraint fk_direction_description_id foreign key (id_description) references description(id_description)
-- )

--alter table direction modify column text TEXT;

-- CREATE TABLE direction_cities (
--        id_city              int NOT NULL,
--        id_direction         int NOT NULL,
--        constraint fk_direction_cities_id_city foreign key (id_city) references city(id_city) on delete cascade,
--        constraint fk_direction_cities_id_direction foreign key (id_direction) references direction(id_direction) on delete cascade
-- )


-- CREATE TABLE direction_countries (
--        id_country           int NOT NULL,
--        id_direction         int NOT NULL,
--        constraint fk_direction_countries_id_country foreign key (id_country) references country(id_country) on delete cascade,
--        constraint fk_direction_countrues_id_direction foreign key (id_direction) references direction(id_direction) on delete cascade
-- )

-- CREATE TABLE direction_stay_hotels (
--        id_stay              int NOT NULL,
--        stay_no              int NOT NULL,
--        id_hotel             int NOT NULL,
--        id_direction         int NOT NULL,
--        status               TINYINT default 1,
--        primary key (id_stay),
--        unique (id_stay),
--        constraint fk_direction_stay_hotels_id_hotel foreign key (id_hotel) references hotel(id_hotel),
--        constraint fk_direction_stay_hotels_id_direction foreign key (id_direction) references direction(id_direction)
-- )

--alter table direction_stay_hotels modify column id_stay int not null auto_increment;

-- CREATE TABLE room_type (
--     id_room_type int not null auto_increment,
--     name_room_type   varchar(20) not null,
--     primary key (id_room_type),
--     unique (id_room_type)
-- );


--insert into room_type(name_room_type) values ("Budget"), ("Economy"), ("Moderate"), ("Deluxe"), ("Premium");

-- CREATE TABLE hotel_rooms (
--     id_room int not null auto_increment,
--     id_hotel int not null,
--     id_room_type int not null,
--     price float,
--     primary key (id_room),
--     unique (id_room),
--     constraint fk_hotel_rooms_id_hotel foreign key (id_hotel) references hotel(id_hotel),
--     constraint fk_hotel_room_id_room_type foreign key (id_room_type) references room_type(id_room_type)
-- );


-- CREATE TABLE tour (
--     id_tour int not null auto_increment,
--     id_direction int not null,
--     departure_date date,
--     days_count  int,
--     price   float,
--     discount    int,
--     total_seats int,
--     free_seats int default 0,
--     primary key (id_tour),
--     unique (id_tour),
--     constraint fk_tour_id_direction foreign key (id_direction) references direction(id_direction)
-- 
--     )



-- CREATE TABLE orders (
--     id_order    int NOT NULL auto_increment,
--     id_user     int not null,
--     id_tour     int not null,
--     seats       int not null,
--     current_price float not null,
--     current_discount int not null,
--     current_user_discount int not null,
--     final_price int not null,
--     orderDate   date,
--     primary key(id_order),
--     unique(id_order),
--     constraint fk_order_id_user foreign key (id_user) references user(id_user),
--     constraint fk_order_id_tour foreign key (id_tour) references tour(id_tour)
--        
-- );

-- create table tourist (
--     id_tourist int not null auto_increment,
--     id_order int not null,
--     first_name varchar(60),
--     middle_name varchar(60),
--     last_name varchar(60),
--     birth_date date,
--     passport varchar(20),
--     primary key (id_tourist),
--     unique(id_tourist),
--     constraint fk_tourist_id_order foreign key (id_order) references orders(id_order)
-- );



    
    
