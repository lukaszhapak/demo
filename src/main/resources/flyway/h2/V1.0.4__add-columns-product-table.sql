alter table product add cron_value boolean;
alter table product add kafka_value varchar(255);
alter table product add client_value varchar(255);

update product set cron_value = false where 1 = 1;

alter table product alter column cron_value set not null;
