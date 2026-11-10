-- 3 --

select to_char (salary, '99,999.00 $') as salary from employees;

select to_char (hire_date, 'YYYY-MM-DD (fmDay)') from employees;

select * from employees where hire_date > to_date ('15-02-2020', 'DD-MM-YYYY');

select to_number('12,500.75', '99,999.00') from dual;

select upper (trim(first_name)) from employees;

select nvl (commission_pct, 0) from employees;

select nvl (COMMISSION_PCT, 'yes', 'no') from employees;