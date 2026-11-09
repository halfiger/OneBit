
select * from studentz;

update studentz set last_name = 'ok' where 2=2;

create table gradez (
  id number,
  name varchar2 (22),
  id_student number,
  constraint cs1
                    foreign key (id_student)
                    references studentz (id)
);

alter table studentz add (
    email varchar2 (22)
    );

create table studentz2 (
  id number constraint cs1 unique,
  country varchar2 (22),
  city varchar2 (22),
    constraint cs2
    check (id>0)
);

create table studentz3 (
  id number,
  name varchar2 (22),
  email varchar2 (22),
  address_id varchar2 (22),
  birthday date

);



