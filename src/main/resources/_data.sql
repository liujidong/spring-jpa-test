--ij
-- connect 'jdbc:derby://localhost:1527/db-test;create=true;user=boot';
--create sequence hibernate_sequence as int;
insert into person(id,name,age,address) values(next value for hibernate_sequence,'汪云飞',32,'合肥');
insert into person(id,name,age,address) values(next value for hibernate_sequence,'xx',31,'北京');
insert into person(id,name,age,address) values(next value for hibernate_sequence,'yy',30,'上海');
insert into person(id,name,age,address) values(next value for hibernate_sequence,'zz',29,'南京');
insert into person(id,name,age,address) values(next value for hibernate_sequence,'aa',28,'武汉');
insert into person(id,name,age,address) values(next value for hibernate_sequence,'bb',27,'合肥');	