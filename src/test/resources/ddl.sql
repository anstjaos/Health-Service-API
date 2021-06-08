create table if not exists Service_User
(
    user_num        int auto_increment primary key,
    user_id         varchar(45),
    nickname        varchar(25),
    password        varchar(256),
    created_on      datetime,
    modified_on     datetime,
    email           varchar(40),
    token           varchar(256)
);

create table if not exists Body_Part
(
    body_part_id         int auto_increment primary key,
    body_part_name       varchar(45)
);

create table if not exists Exercise_Type
(
    type_id         int auto_increment primary key,
    type_name       varchar(45)
);

create table if not exists Exercise
(
    exercise_id         int auto_increment primary key,
    name                varchar(45),
    exercise_type_id    int,
    body_part_id        int
);

create table if not exists Exercise_Routine
(
    routine_id      int auto_increment primary key,
    user_num        int,
    routine_name    varchar(45),
    day_of_week     int
);

create table if not exists Map_Exercise_Routine
(
    map_id      int auto_increment primary key,
    exercise_id int,
    routine_id  int
);

create table if not exists Map_Exercise_User
(
    map_id              int auto_increment primary key,
    exercise_id         int,
    user_num            int,
    date                date,
    exercise_count      int,
    set_count           int
);
