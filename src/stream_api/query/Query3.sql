-- 3 --

select to_char(salary, 'fm99,999.00 $')
from employees;

select to_char(hire_date, 'YYYY-MM-DD (fmDay)')
           as hire_day
from employees;

select *
from employees
where hire_date > to_date('15-02-2020', 'DD-MM-YYYY');

select to_number('12,500.75', '99,999.00 $')
from dual;

select upper(trim(first_name))
from employees;

select nvl(commission_pct, 0)
from employees;

select nvl2(commission_pct, 'yes', 'no')
from employees;

select nullif(salary, 9000)
from employees;

select coalesce(commission_pct, salary, 0)
from employees;

select decode(salary, 9000, null, salary)
from employees;

select case department_id
           when 10 then 'it'
           when 20 then 'hr'
           else 'default'
           end
           as result
from employees;

select case
           when salary < 4000 then 'low'
           when salary between 4000 and 8000 then 'medium'
           else 'high'
           end as result
from employees;

select nvl(to_char(salary, 'fm99.999,00'), 'no salary')
           as result
from employees;

select case
           when hire_date > to_date('01-01-2023', 'DD-MM-YYYY')
               then 'new'
           else 'old'
           end as result
from employees;

select EMPLOYEES.EMPLOYEE_ID, coalesce(commission_pct, salary, 1000) as salary
from employees;

select case
           when length(first_name) > 5 then upper(first_name)
           else first_name
           end as result
from employees;

select nvl(nullif(salary, 0), 999)
from employees;

select case department_id
           when 10 then 'it'
           when 20 then 'hr'
           else 'default'
           end as result
from employees;

select employee_id,
       to_char(hire_date, 'YYYY/MM/DD'),
       nvl2(commission_pct, 'has', 'no')
from employees;

select * from employees where COMMISSION_PCT is null or COMMISSION_PCT = 0;

select count (*) from employees
                 where commission_pct is not null;

select count (DECODE(COMMISSION_PCT, null, 0, 1))
from employees;

select nvl2 (COMMISSION_PCT, 'bonus', 'none') from employees;

select nullif (salary, 6000) as sal from employees;

select coalesce (COMMISSION_PCT, SALARY, 0) as value from employees;

select decode (COMMISSION_PCT, null, 'none', 'has') from employees;

select case COMMISSION_PCT
    when 0 then 'zero'
    when 0.1 then 'low'
    when 0.2 then 'high'
    when null then 'none'
end as commission
from employees;

select case trunc (SALARY/1000)
when 3 then 'low'
when 4 then 'mid'
else 'high'
end as salary_result
from employees;

select case
    when salary is null then 'no data'
    when salary < 4000 then 'low'
    else 'ok'
    end as result
    from employees;


