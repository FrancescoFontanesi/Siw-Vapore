
/*adventure*/


insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Minecraft','Adventure',2008,'questa è la descrizione di minecraft','/images/minecraftCopertina.jpg',    ARRAY['minecraft/img1.jpg', 'minecraft/img2.jpg', 'minecraft/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'The last of Us','Adventure',2010,'questa è la descrizione di tlou','/images/theLastOfUsCopertina.jpg' , ARRAY['theLastOfUs/img1.jpg', 'theLastOfUs/img2.jpg', 'theLastOfUs/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Destiny','Adventure',2015,'questa è la descrizione di destiny','/images/destinyCopertina.jpg' , ARRAY['destiny/img1.jpg', 'destiny/img2.jpg', 'destiny/img3.jpg']);

insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Minecraft','Adventure',2008,'questa è la descrizione di minecraft','/images/minecraftCopertina.jpg',    ARRAY['minecraft/img1.jpg', 'minecraft/img2.jpg', 'minecraft/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'The last of Us','Adventure',2010,'questa è la descrizione di tlou','/images/theLastOfUsCopertina.jpg' , ARRAY['theLastOfUs/img1.jpg', 'theLastOfUs/img2.jpg', 'theLastOfUs/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Destiny','Adventure',2015,'questa è la descrizione di destiny','/images/destinyCopertina.jpg' , ARRAY['destiny/img1.jpg', 'destiny/img2.jpg', 'destiny/img3.jpg']);

/*action*/


insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Minecraft','Action',2008,'questa è la descrizione di minecraft','/images/minecraft.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'The last of Us','Action',2010,'questa è la descrizione di tlou','/images/theLastOfUs.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Destiny','Action',2015,'questa è la descrizione di destiny','/images/destiny.jpg');





INSERT INTO utente (id,name) values (nextval('utente_seq'),'admin');
INSERT INTO credentials (id, email, password, role, user_id) VALUES (nextval('credentials_seq'),'admin@example.com', 'password', 'ADMIN', (SELECT id FROM utente where id = 1));
