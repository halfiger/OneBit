-- 2 --

select * from employees where salary between 3000 and 7000;

select * from employees where department_id in (10, 20, 50);

select * from employees where commission_pct is null;

select * from EMPLOYEES where last_name like 'A%';

select * from employees where job_id like '%$_%' escape '$';

select * from employees where salary > 6000 and department_id not in (10, 50);

select * from employees order by commission_pct nulls last;

select last_name, salary from employees order by 1, 2 desc;

select upper (last_name) from employees where length (last_name) > 7;

select sysdate + 6 from dual;

select add_months(sysdate, 6) from dual;

select round (salary, -2) as rounded, trunc (salary, -2) as trunced from employees;

select * from employees where mod (employee_id, 2) = 0;

select mod (salary, 1000) from employees;

select months_between (hire_date, sysdate) as months from employees;

select add_months (hire_date, 12) from employees;

select next_day(hire_date, 'friday') from employees;

select last_day (hire_date) from employees;

select round (hire_date, 'month') from employees;

select trunc (hire_date, 'month') from employees;

select round (hire_date, 'day') as rounded, trunc (hire_date, 'day') from employees;

select initcap (first_name) from employees;

select trim ('#' from '#ADA#M##') as result from dual;

select substr (last_name, 1, 3) from employees;

select substr(last_name, -2) from employees;

select lpad (salary, 10, '#') from employees;

select instr (lower(first_name), 'a') from employees;

select replace (first_name, 'a', '@') from employees;

select replace (first_name, 'a', '') from employees;

select to_char(sysdate, 'DD-MON-RR_HH-MM-SS') from dual;

select sysdate + 7 from dual;

select sysdate +3/24;