-- 4 --

select count(*) as allrows, count(commission_pct) as commission
from employees;

select sum(salary) as allsalary
from employees;

select round(avg(salary), 2)
from employees;

--4 min max + date

select min(hire_date), max(hire_date)
from employees;

select department_id, avg(salary)
from employees
group by department_id;

select department_id, count(*)
from employees
group by department_id;

select department_id, job_id, avg(salary)
from employees
group by department_id, job_id;

select department_id, avg(salary)
from employees
group by department_id
having avg(salary) > 7000;

select department_id, count(*)
from employees
group by department_id
having count(*) > 5;

select department_id, max(salary)
from employees
where salary > 3000
group by department_id
having max(salary) > 10000;

select department_id, count(manager_id) as with_manager_id
from EMPLOYEES
group by department_id;

select count(distinct DEPARTMENT_ID)
from employees;

select case
           when salary < 4000 then 'low'
           when salary between 4000 and 7999 then 'medium'
           else 'high'
           end as salary
from EMPLOYEES
group by case
             when salary < 4000 then 'low'
             when salary between 4000 and 7999 then 'medium'
             else 'high'
             end;

select case
           when commission_pct is null then 'has'
           else 'none'
           end as commission
from EMPLOYEES
group by case
             when commission_pct is null then 'has'
             else 'none'
             end;

select sum(case when commission_pct is not null then 1 else 0 end) as has,
       sum(case when commission_pct is null then 1 else 0 end)     as none
from employees;

select DECODE(DEPARTMENT_ID, 50, '50', 'other'),
       sum(salary)
from EMPLOYEES
group by DECODE(DEPARTMENT_ID, 50, '50', 'other');

select nvl(to_char(manager_id), 'no manager')
     , count(*)
from EMPLOYEES
group by manager_id;

select case
           when salary < 4000 then 'low'
           when salary between 4000 and 7999 then 'medium'
           else 'high' end as salary,
       count(*)
from EMPLOYEES
group by case
             when salary < 4000 then 'low'
             when salary between 4000 and 7999 then 'medium'
             else 'high' end;

select department_id, count(*)
from EMPLOYEES
group by department_id;

select case
           when COMMISSION_PCT is null then 'none'
           else 'has' end,
       avg(salary)
from EMPLOYEES
group by case
             when COMMISSION_PCT is null then 'none'
             else 'has' end;

select case
           when manager_id is not null then 'none'
           else 'has' end as has_manager,
       count(*)
from EMPLOYEES
group by case
             when manager_id is not null then 'none'
             else 'has' end
having avg(salary) > 6000;

select department_id as dep,
       count(*)
from EMPLOYEES
group by department_id
order by dep desc;

select department_id, count(*)
from EMPLOYEES
where salary > 6000
group by department_id;

select avg(salary)
from employees
having avg(salary) > 6000;

select department_id, count(distinct job_id)
from EMPLOYEES
group by department_id;

select to_number(hire_date, 'YYYY') as year, count(*)
from EMPLOYEES
group by to_number(hire_date, 'YYYY');

select nvl(commission_pct, 0), count(*)
from EMPLOYEES
group by commission_pct;

select case
           when DEPARTMENT_ID < 50 then 'small'
           else 'big' end
from EMPLOYEES
group by case
             when DEPARTMENT_ID < 50 then 'small'
             else 'big' end;

select DEPARTMENT_ID, count(*)
from EMPLOYEES
group by DEPARTMENT_ID
having avg(salary) > 5000
   and count(*) > 3;




