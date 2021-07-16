create schema if not exists dbcity collate utf8_general_ci;

create table if not exists location
(
	id int auto_increment
		primary key,
	name varchar(45) not null,
	constraint name_UNIQUE
		unique (name)
);

create table if not exists problem
(
	id int not null,
	from_id int not null,
	to_id int null,
	constraint id_UNIQUE
		unique (id),
	constraint fk_from_id_prob
		foreign key (from_id) references location (id),
	constraint fk_to_id_prob
		foreign key (to_id) references location (id)
);

create index fk_from_id_idx
	on problem (from_id);

create index fk_to_id_idx
	on problem (to_id);

alter table problem
	add primary key (id);

create table if not exists route
(
	id int auto_increment
		primary key,
	from_id int null,
	to_id int null,
	cost int null,
	constraint fk_from_id
		foreign key (from_id) references location (id),
	constraint fk_to_id
		foreign key (to_id) references location (id)
);

create index fk_from_id_idx
	on route (from_id);

create index fk_to_id_idx
	on route (to_id);

create table if not exists solution
(
	problem_id int not null
		primary key,
	cost int null,
	constraint fk_problem
		foreign key (problem_id) references problem (id)
			on delete cascade
);

INSERT INTO `dbcity`.`location` (`id`, `name`) VALUES ('1', 'gdansk');
INSERT INTO `dbcity`.`location` (`id`, `name`) VALUES ('2', 'bydgoszcz');
INSERT INTO `dbcity`.`location` (`id`, `name`) VALUES ('3', 'torun');
INSERT INTO `dbcity`.`location` (`id`, `name`) VALUES ('4', 'warszawa');

INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('1', '1', '2', '1');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('2', '1', '3', '3');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('3', '2', '1', '1');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('4', '2', '3', '1');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('5', '2', '4', '4');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('6', '3', '1', '3');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('7', '3', '2', '1');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('8', '3', '4', '1');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('9', '4', '2', '4');
INSERT INTO `dbcity`.`route` (`id`, `from_id`, `to_id`, `cost`) VALUES ('10', '4', '3', '1');

INSERT INTO `dbcity`.`problem` (`id`, `from_id`, `to_id`) VALUES ('1', '1', '4');
INSERT INTO `dbcity`.`problem` (`id`, `from_id`, `to_id`) VALUES ('2', '2', '4');
