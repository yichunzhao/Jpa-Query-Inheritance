delete from release where id in (select id from release where id is not null);
delete from application where id in (select id from application where id is not null);