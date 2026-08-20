insert into studentz (id, first_name, last_name, birth_date)
values (1, 'boba', 'baba', to_date('10-03-2024', 'DD-MM-YYYY'));

select *
from studentz;

create table productz
(
    id   number primary key,
    name varchar2(10) not null
);


alter table productz
    add (
        surname varchar2(12)
        );

insert into productz (id, name, surname)
values (1, 'bibi', null);

insert into productz (id, name, surname)
values (2, UPPER('koko'), lower('KIKI'));


select *
from productz;

create table employeez2
(
    id        number primary key,
    full_name varchar2(12),
    salary    number CHECK (salary > 1),
    CHECK (salary > 0)
);
drop table employeez2;

update productz
set surname = nvl(surname)
where surname is null;
update productz
set surname = coalesce(surname, 'TITI')
where surname is null;
select *
from productz;

create table orderz
(
    id    number primary key,
    name  varchar2(12) default 'none',
    price number       default 0
);

create table orderz2
(
    order_id    number primary key,
    amount      number,
    customer_id number,
    constraint f_k
        foreign key (customer_id)
            references orderz (id)
);

create table meetingz
(
    meetings_id number primary key,
    topic       varchar2(16),
    created_at  date default sysdate
)
;



insert into meetingz (meetings_id, topic, created_at)
VALUES (1, 'hello', to_date('25-DEC-2025', 'DD-MON-YYYY'));
select *
from meetingz;

insert into meetingz (meetings_id, topic)
VALUES (2, upper('hello2'));
insert into meetingz (meetings_id, topic)
VALUES (3, upper('hello4'));


create table meet
(
    meet_id number primary key,
    name    varchar2(33),
    fk_id   NUMBER,
    CONSTRAINT F_K FOREIGN KEY (FK_ID) REFERENCES MEETINGZ (ID)
);

update meetingz
set topic = initcap(topic)
where 4 = 4;

select *
from meetingz;

update meetingz
set email = 'none'
where email is null;
update meetingz
set email = 'yes'
where length(topic) > 5;

create table movies
(
    id     number primary key,
    name   varchar2(22),
    rating NUMBER CHECK (rating BETWEEN 1 AND 10)
);

CREATE TABLE MOVZ2
(
    mov_id primary key,
    name    varchar2(33),
    movz_id number,
    constraint mo_z
        foreign key (movz_id)
            references movies (id)
);

select topic, nvl(created_at, sysdate)
from meetingz;

alter table meetingz
    add (
        email varchar2(33)
        );

delete
from meetingz
where created_at is null;

create table friends as (select employee_id, first_name, last_name
                         from employees
                         where commission_pct is null);


select *
from friends;

insert into friends (employee_id, first_name, last_name)
values (99, 'baba', 'aba');

alter table friends rename column employee_id to id;

drop table friends;

create table friends
(
    id       number primary key,
    name     varchar2(16),
    surname  varchar2(16),
    email    varchar2(16),
    salary   number default 1000,
    birthday date   default sysdate
);

alter table friends
    add constraint cc1
        check (salary > 10000);

alter table friends
    add constraint cc2
        unique (email);

alter table friends
    add (
        phone1 number,
        phone2 number
        );

alter table friends
    modify (
        salary number default 1500
        );


alter table friends
    add constraint cc3
        check (salary > 2000);


alter table friends
    drop constraint cc2;

alter table friends rename to friends2;


insert into friends (id, name, surname, email)
values (97, 'kyyaka', 'maldyyini', 'paolyyo');

select *
from friends;

commit;

alter table friends
    drop column salary;

alter table friends
    set unused column email;

alter table friends
    set unused column surname;

alter table friends
    drop unused columns;

select *
from friends;

alter table friends
    read only;

alter table friends
    read write;

truncate table friends;

select *
from friends;

drop table friends;

create table friendz as (select employee_id, first_name, last_name
                         from employees);

create table frz
(
    id     number primary key,
    name   varchar2(22),
    id_frz number,
    constraint cc33
        foreign key (id_frz)
            references friendz (id)
);

create table hrz
(
    id     number primary key,
    name   varchar2(22),
    id_hrz number,
    constraint cc44 foreign key (ID_HRZ) REFERENCES FRIENDZ (ID)
);

DROP TABLE FRZ;
drop table studentz;

create table studentz2
(
    id         number primary key,
    first_name varchar2(22),
    last_name  varchar2(22) CHECK (LENGTH(LAST_NAME) > 0),
    birth_date date not null
);

INSERT INTO STUDENTZ2 (id, first_name, last_name, birth_date)
VALUES (1, 'KOKA', 'KOLA', TO_DATE('15-03-2024', 'DD-MM-YYYY'));

SELECT *
FROM STUDENTZ2;


INSERT INTO STUDENTZ2 (id, first_name, last_name, birth_date)
VALUES (2, NULL, 'BOBA', TO_DATE('15-03-2024', 'DD-MM-YYYY'));

INSERT INTO STUDENTZ2 (id, first_name, last_name, birth_date)
VALUES (3, 'ADMIN', LOWER('NIMDA'), SYSDATE);
SELECT *
FROM STUDENTZ2;

UPDATE STUDENTZ2
SET FIRST_NAME = NVL(FIRST_NAME, 'NONE')
WHERE FIRST_NAME IS NULL;

UPDATE STUDENTZ
SET FIRST_NAME = COALESCE(FIRST_NAME, 'NO NAME')
WHERE FIRST_NAME IS NULL;

create table studentz3
(
    id         number primary key,
    first_name varchar2(22) default 'new',
    last_name  varchar2(22) CHECK (LENGTH(LAST_NAME) > 0),
    birth_date date not null
);

select *
from studentz3;

drop table studentz;

create table teacherz4
(
    t_id number primary key,
    name varchar2(22),
    s_id number,
    constraint cc36
        foreign key (s_id)
            references studentz2 (id)
);

create table studentz4
(
    id         number primary key,
    first_name varchar2(22) default 'new',
    last_name  varchar2(22) CHECK (LENGTH(LAST_NAME) > 0),
    birth_date date         DEFAULT SYSDATE not null
);

insert into studentz4 (id, first_name, last_name, birth_date)
VALUES (1, 'boba', 'feta', to_date('25-dec-2025', 'DD-MON-YYYY'));
select *
from studentz4;

insert into studentz4 (id, first_name, last_name)
values (2, upper('bibi'), 'bebe');
select *
from studentz4;

update studentz4
set last_name = initcap(LAST_NAME)
where 7 = 7;
select *
from studentz4;

alter table studentz4
    add (
        grade number,
        constraint cc37 CHECK (grade between 1 and 10)
        );

select id,
       first_name,
       last_name,
       nvl(studentz4.grade, 0)
           as grade
from studentz4;

alter table studentz4
    add (
        email varchar2(100)
        );
select *
from studentz4;

update studentz4
set grade = 4
where 4 = 4;
insert into studentz4 (id, first_name, last_name, birth_date, grade)
VALUES (3, 'boni', 'nani', to_date('25-dec-2025', 'DD-MON-YYYY'), 7);
select *
from studentz4;
update studentz4
set grade = grade + 3
where grade < (select avg(grade) from studentz4);


delete studentz4
where email is null;
select *
from studentz4;

drop table friendz;

create table friendz (id, name, surname)
as (select employee_id, first_name, last_name
    from employees);
select *
from friendz;

alter table friendz
    modify (
        email varchar2(22) default 'none'
        );

insert into friendz (id, name, surname)
values (99, 'koka', 'kola');

alter table friendz rename column id to id2;

drop table friendz;

create table friendz
(
    id       number primary key,
    name     varchar2(22),
    surname  varchar2(22),
    email    varchar2(22),
    salary   number default 1000,
    birthday date   default sysdate - 1000
);

alter table friendz
    add constraint ccc1
        check (salary > 0);

alter table friendz
    add constraint ccc2
        unique (email);

alter table friendz
    add (
        phone1 number,
        phone2 number
        );

select *
from friendz;

alter table friendz
    modify (
        email varchar2(100) default 'none'
        );

alter table friendz
    add constraint ccc3
        check (salary > 100);

alter table friendz
    add constraint ccc4
        unique (surname);

alter table friendz
    drop constraint ccc4;

alter table friendz rename to friendz2;
select *
from friendz2;

insert into friendz (id, name, surname, email, salary, BIRTHDAY)
values (1, 'koka', 'kola', 'k@kola', 1111, sysdate - 5);

insert into friendz (id, name, surname, email)
values (2, 'boka', 'bola', 'b@bola');

select *
from friendz;
commit;
alter table friendz
    drop column salary;

alter table friendz
    set unused column email;
alter table friendz
    set unused column surname;

alter table friendz
    drop unused columns;

alter table friendz
    read only;

insert into friendz (id, name)
values (3, 'moka');

alter table friendz
    read write;

truncate table friendz;

drop table friendz;

create table studentz
(
    id         number primary key,
    first_name varchar2(22),
    last_name  varchar2(22),
    birthdate  date
);

DROP TABLE STUDENTZ;

create table t2
(
    id   number primary key,
    name varchar2(22) not null,
    id_s number,
    constraint sss6
        foreign key (id_s)
            references studentz (id)
);
DROP TABLE T2;

create table studentz22
(
    id         number primary key,
    first_name varchar2(22) check (length(first_name) > 0),
    last_name  varchar2(22) DEFAULT 'NEW',
    birthdate  date
);
drop table studentz2;

CREATE TABLE F10Z
(
    ID    NUMBER PRIMARY KEY,
    NAME  VARCHAR2(22),
    ST_ID NUMBER,
    CONSTRAINT CCC67
        FOREIGN KEY (ST_ID)
            REFERENCES STUDENTZ22 (ID)
);

DROP TABLE f10z;

insert into studentz22 (id, first_name, last_name, birthdate)
VALUES (1, 'john', 'smith', to_date('15-MAR-2024', 'DD-MON-YYYY'));
select *
from studentz22;

insert into studentz22 (id, first_name, birthdate)
VALUES (2, 'john', to_date('15-MAR-2024', 'DD-MON-YYYY'));
select *
from studentz22;

insert into studentz22 (id, first_name, last_name, birthdate)
VALUES (3, upper('admin'), null, sysdate);

select *
from studentz22;

update STUDENTZ22
SET LAST_NAME = 'NONE'
WHERE LAST_NAME IS NULL;

CREATE TABLE STUDENTZ4
(
    ID         NUMBER PRIMARY KEY,
    FIRST_NAME VARCHAR2(22),
    LAST_NAME  VARCHAR2(22),
    BIRTHDAY   DATE DEFAULT SYSDATE
);

DROP TABLE STUDENTZ4;

INSERT INTO STUDENTZ4 (id, first_name, last_name, birthDAY)
VALUES (1, 'BABA', NULL, TO_DATE('25-DEC-2026', 'DD-MON-YYYY'));

INSERT INTO STUDENTZ4 (id, first_name, last_name)
VALUES (3, 'BABA', UPPER('PAPA'));

SELECT *
FROM STUDENTZ4;

UPDATE STUDENTZ4
SET FIRST_NAME = INITCAP(first_name)
WHERE 5 = 5;

ALTER TABLE STUDENTZ4
    ADD (
        GRADE NUMBER CHECK (GRADE > 0 AND GRADE < 10)
        );

ALTER TABLE STUDENTZ4
    MODIFY (
        LAST_NAME VARCHAR2(100) DEFAULT 'NONE'
        );

insert into studentz4 (id, first_name, last_name, birthdaY, grade)
VALUES (2, 'boni', 'nani', to_date('25-dec-2025', 'DD-MON-YYYY'), 7);
select *
from studentz4;

update studentz4
set grade = 4
where 4 = 4;

UPDATE STUDENTZ4
SET GRADE = GRADE + 3
WHERE GRADE = (SELECT AVG(GRADE) FROM STUDENTZ4);

ALTER TABLE STUDENTZ4
    ADD (
        EMAIL VARCHAR2(100)
        );

SELECT ID, FIRST_NAME, LAST_NAME, NVL(GRADE, 0) AS GRADE
FROM STUDENTZ4;

SELECT *
FROM STUDENTZ4;

DELETE STUDENTZ4
WHERE LAST_NAME IS NULL;

DROP TABLE STUDENTZ2;

CREATE TABLE STUDENTZ2
(
    ID         NUMBER PRIMARY KEY,
    FIRST_NAME VARCHAR2(22) default 'sam',
    LAST_NAME  VARCHAR2(22) CHECK (LENGTH(LAST_NAME) > 0),
    BIRT_DATE  DATE NOT NULL
);
COMMIT;

INSERT INTO STUDENTZ2 (id, first_name, last_name, birt_date)
VALUES (3, 'admin', upper('asldfkas'), sysdate);

select *
from studentz2;

update studentz2
set first_name = nvl(first_name, 'none')
where first_name is null;

update studentz2
set last_name = 'vivi'
where LAST_NAME = '222';

drop table studentz;

create table gradez
(
    id        number primary key,
    name      varchar2(22),
    id_gradez number,
    constraint ss1
        foreign key (id_gradez)
            references studentz2 (id)
);

drop table gradez;

drop table studentz2;

create table studentz4
(
    id         number primary key,
    first_name varchar2(22),
    last_name  varchar2(22),
    birth_date date default sysdate
);

insert into studentz4 (id, first_name, last_name, birth_date)
VALUES (2, upper('didu'), null, to_date('25-dec-2025', 'DD-mon-YYYY'));

select *
from studentz4;

update studentz4
set first_name = initcap(first_name)
where 1 = 1;

alter table studentz4
    add (
        grades number check (grades > 0 and grades <= 10)
        );

select first_name, nvl(grades, 0)
from studentz4;

alter table studentz4
    add (
        email varchar2(100)
        );

update studentz4
set grades = 4
where 4 = 4;

insert into studentz4 (id, first_name, last_name, birth_date, grades)
VALUES (3, 'boni', null, to_date('25-dec-2025', 'DD-MON-YYYY'), 7);

update studentz4
set grades = grades + 3
where grades < (select avg(grades) from studentz4);

delete studentz4
where email is null;
commit;

create table friendz (id, name, surname)
as (select employee_id, first_name, last_name
    from employees);

alter table friendz
    add (
        email varchar2(22)
        );

alter table friendz
    modify (
        email varchar2(100) default 'no email'
        );

insert into friendz (id, name, surname)
VALUES (1, 'john', 'johns');

alter table friendz rename column email to emails;
drop table friendz;

create table friendz
(
    id       number primary key,
    name     varchar2(22),
    surname  varchar2(22),
    email    varchar2(22),
    salary   number default 1000,
    birthday date   default sysdate - 1000
);

insert into friendz (id, name, surname, email, salary, birthday)
values (1, 'boba', 'feta', 'boba@mail', 1100, sysdate);

insert into friendz (id, name, surname, email)
values (2, 'coca', 'cola', 'coca@mail');

select *
from friendz;

commit;

alter table friendz
    drop column email;

alter table friendz
    set unused column salary;

alter table friendz
    set unused column surname;

alter table friendz
    drop unused columns;

alter table friendz
    read only;

alter table friendz
    read write;

truncate table friendz;

drop table friendz;

alter table friendz
    add constraint cs1
        check (salary > 500);

alter table friendz
    add constraint cs2
        unique (surname);

alter table friendz
    add (
        phone1 number,
        phone3 number
        );

alter table friendz
    modify (
        email varchar2(110) default 'none'
        );

alter table friendz
    add constraint cc2
        check (phone3 > 600);

alter table friendz
    drop constraint cs1;

alter table friendz
    read only;
alter table friendz
    read write;

alter table friendz rename to friend;
commit;

drop table friend;

create table addrezz
(
    id      number
        constraint ad_id_un unique,
    country varchar2(22),
    city    varchar2(22)
);

ALTER TABLE ADREZZZ
    DROP CONSTRAINT CS2;

CREATE UNIQUE INDEX CS7 ON ADDREZZ (CITY);

CREATE BITMAP INDEX CS8 ON ADDREZZ (COUNTRY);

DROP INDEX CS8;

create table adrezzz
(
    id      number
        constraint cs1 unique,
    country varchar2(22)
        constraint cs0 check (length(country) > 3),
    city    varchar2(22),
    constraint cs2
        unique (city)
);

create table cityzz
(
    id         number unique,
    name       varchar2(22),
    QQ_ID      NUMBER,
    address_id number references adrezzz (id)
                          ON DELETE SET NULL,
    CHECK (LENGTH(name) > 3),

    CONSTRAINT CS9
        FOREIGN KEY (QQ_ID)
            REFERENCES ADREZZZ (ID)
                ON DELETE CASCADE
);


create table cityz
(
    id      number,
    name    varchar2(22),
    code_id number,
    constraint cs3
        foreign key (CODE_ID)
            REFERENCES ADDREZZ (ID)
                ON DELETE SET NULL
);

ALTER TABLE CITYZ
    MODIFY
        NAME CONSTRAINT CS6 NOT NULL;

alter table cityz
    modify (
        id number primary key
        );

alter table cityz
    add constraint cs5
        primary key (ID);


-- 08-17-26 --

create table studentz
(
    id         number primary key,
    first_name varchar2(22) not null,
    last_name  varchar2(22) default 'new',
    age        number check (age > 0),
    birth_date date
);

create table grades
(
    id    number,
    name  varchar2(22),
    id_st number,
    constraint cs1
        foreign key (id_st)
            references studentz (id)
);

insert into studentz (id, first_name, last_name, age, birth_date)
values (1, 'john', 'smith', 22, to_date('15-MAR-2024', 'DD-MON-YYYY'));

insert into studentz (id, first_name, last_name, age, birth_date)
values (2, 'bob', 'roberts', 24, null);

insert into studentz (id, first_name, last_name, age, birth_date)
values (3, upper('admin'), null, 24, sysdate);

update studentz
set last_name = 'none'
where last_name is null;

select *
from studentz;

create table studentz4
(
    id         number primary key,
    first_name varchar2(22),
    last_name  varchar2(22),
    birth_date date default sysdate
);

alter table studentz4
    add grade number check (grade between 1 and 12);
alter table studentz4
    add grade2
        constraint cs2 check (grade between 1 and 12);

insert into studentz4 (id, first_name, last_name, birth_date)
values (2, UPPER('dudu'), null, to_date('25-dec-2025', 'DD-MON-YYYY'));

select *
FROM STUDENTZ4;

update studentz4
set first_name = initcap(first_name)
where 4 = 4;

select id, first_name, nvl(grade, 0)
from studentz4;

alter table studentz4
    add (
        email varchar2(100)
        );

update studentz4
set grade = 4
where 4 = 4;
insert into studentz4 (id, first_name, last_name, birth_date, grade)
VALUES (3, 'boni', null, to_date('25-dec-2025', 'DD-MON-YYYY'), 7);

update studentz4
set grade = grade + 3
where grade < (select avg(grade) from studentz4);

delete studentz4
where email is null;

create table friendz (id, name, surname) as
    (select employee_id, first_name, last_name
     from employees
     where commission_pct is null);

alter table friendz
    add (
        email varchar2(22)
        );

alter table friendz
    modify (
        email varchar2(100) default 'none'
        );

alter table friendz rename column email to mail;

select *
from friendz;

drop table friendz;

drop table studentz;

drop table studentz4;

create table friendz
(
    id       number,
    name     varchar2(22),
    surname  varchar2(22),
    email    varchar2(22),
    salary   number default 1000,
    birthday date   default sysdate
);

commit;

alter table friendz
    drop column email;

alter table friendz
    set unused column email;

alter table friendz
    set unused column salary;

alter table friendz
    drop unused columns;

select *
from friendz;

alter table friendz
    read only;

alter table friendz
    read write;

truncate table friendz;

drop table friendz;


alter table friendz
    add constraint cs3 check (salary > 0);

alter table friendz
    add constraint cs4 unique (mail);

alter table friendz
    add (
        phone1 number,
        phone2 number
        );

alter table friendz
    modify mail varchar2(200) default 'none2';

alter table friendz
    drop constraint cs1;

-- block 14 --

create table addrezz
(
    id      number
        constraint ad_in_un unique,
    country varchar2(22),
    city    varchar2(22),
    constraint ad_out_un
        unique (id)
);
drop table friendz;

create table friendz
(
    id         number,
    name       varchar2(22) check (length(name) > 3),
    email      varchar2(22) not null,
    addrezz_id number       REFERENCES addrezz (id) on delete set null,
    birthday   date,
    constraint cst2
        foreign key (addrezz_id)
            REFERENCES addrezz (id)
                on delete set null,
    check (length(name) > 4)
);

alter table friendz
    modify (
        id number primary key
        );

alter table friendz
    add constraint ccsrt5 primary key (id);

alter table friendz
    modify (
        email varchar2(22) not null
        );

--btree index
create unique index fr_fr_2 on friendz (id);
create unique index fr_fr_3 on friendz (email);

--bitmap index
create bitmap index fr_fr_4 on friendz (addrezz_id);

drop index fr_fr_4;

--ON DELETE CASCADE
create table friendz
(
    id         number,
    name       varchar2(22),
    email      varchar2(22),
    addrezz_id number,
    birthday   date,
    constraint cs_cs_1
        foreign key (addrezz_id)
            references friendz (id)
                on delete cascade
);

create table friendz
(
    id     number,
    name   varchar2(22),
    salary number check (salary between 500 and 1000)
);

create table friendz
(
    id     number constraint cscs6 unique constraint cscs7 not null,
    name   varchar2(22),
    salary number,
    email varchar2(22),
    constraint cscs4
check (salary between 500 and 1000),
constraint cscs5
check (email like '%@%')

);

