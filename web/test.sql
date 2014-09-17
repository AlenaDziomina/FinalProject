-- create table direction (
--     id_dir int not null auto_increment,
--     dir_name varchar(20),
--     primary key (id_dir),
--     unique(id_dir)
-- );

-- insert into direction values (1, "name1"), (2, "name2"), (3, "name3");

-- select * from direction;

-- create table countryes (
--     id_count int not null auto_increment,
--     count_name varchar(20),
--     primary key (id_count),
--     unique(id_count)
-- );
-- 
-- create table direction_countrys (
--     id_dir int not null,
--     id_count int not null,
--     constraint fk_dir_count_id_dir foreign key (id_dir) references direction(id_dir),
--     constraint fk_dir_count_id_count foreign key (id_count) references countryes(id_count)
-- );

-- insert into countryes values (1, "polsk"), (2, "litva"), (3, "germany"), (4, "frans");

-- insert into direction_countrys values (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 4);

-- select d.dir_name, c.count_name from direction d
--     inner join direction_countrys dc on (dc.id_dir = d.id_dir)
--     inner join countryes c on (dc.id_count = c.id_count and c.count_name = "litva");
--     where c.count_name = "litva";

select * from peoples;
-- insert into peoples(f_fio) values ('Grouk');

-- alter table peoples modify column id int not null auto_increment;

