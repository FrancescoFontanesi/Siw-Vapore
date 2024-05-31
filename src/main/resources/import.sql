
/*adventure*/
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Minecraft','Adventure',2008,'questa è la descrizione di minecraft','/images/minecraft.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'The last of Us','Adventure',2010,'questa è la descrizione di tlou','/images/theLastOfUs.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Destiny','Adventure',2015,'questa è la descrizione di destiny','/images/destiny.jpg');

insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Minecraft','Adventure',2008,'questa è la descrizione di minecraft','/images/minecraft.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'The last of Us','Adventure',2010,'questa è la descrizione di tlou','/images/theLastOfUs.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Destiny','Adventure',2015,'questa è la descrizione di destiny','/images/destiny.jpg');

insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Minecraft','Adventure',2008,'questa è la descrizione di minecraft','/images/minecraft.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'The last of Us','Adventure',2010,'questa è la descrizione di tlou','/images/theLastOfUs.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Destiny','Adventure',2015,'questa è la descrizione di destiny','/images/destiny.jpg');

/*action*/


insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Minecraft','Action',2008,'questa è la descrizione di minecraft','/images/minecraft.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'The last of Us','Action',2010,'questa è la descrizione di tlou','/images/theLastOfUs.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Destiny','Action',2015,'questa è la descrizione di destiny','/images/destiny.jpg');





INSERT INTO utente (id,name) values (nextval('utente_seq'),'Customer');
INSERT INTO credentials (id, email, password, role, user_id) VALUES (nextval('credentials_seq'),'c@example.com', 'password', 'Customer', (SELECT id FROM utente where id = 1));
