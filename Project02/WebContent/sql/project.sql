create table car_member
(
	ID varchar(20) primary key,
    PASS varchar(30) not null,
    NAME varchar(30) not null,
    BIRTH varchar(40) not null,
    GENDER varchar(5) not null,
    PHONENUMBER varchar(50) not null,
    EMAIL varchar(50),
    ADDRESS varchar(100) not null,
    DATE DATETIME default now()
);

create table car_info
(
	BRAND varchar(40) not null,
    CAR_NAME varchar(70) primary key,
    CAR_TYPE varchar(20) not null,
    YEAR varchar(10) not null,
    TRANS varchar(20) not null,
    USER_ID varchar(20) not null
);

create table car_fix
(
	DAY DATETIME default now(),
    PARTS_NAME varchar(50) not null,
    PARTS_CNT int(10) default 1,
    PARTS_MONEY int(20) not null,
    FIX_MONEY int(20),
    TAX int(20) not null,
    MONEY int(30) not null,
    SHOP_NAME varchar(70) not null,
    PAY varchar(10),
    GITA varchar(20),
    USER_ID varchar(20) not null
);

alter table car_info add constraint fk_car_info_id foreign key (user_id) references car_member (id) on delete cascade;

alter table car_fix add constraint fk_car_fix_id foreign key (user_id) references car_member (id) on delete cascade;