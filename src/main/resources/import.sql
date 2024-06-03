
/*adventure*/


insert into game (id,name,category,release_date,description,copertina,images,price) values (nextval('games_seq'),'Minecraft','Adventure',2008,'Minecraft è un videogioco sandbox in cui i giocatori possono esplorare mondi infiniti generati proceduralmente, raccogliere risorse, costruire strutture e sopravvivere contro mob ostili. Offre modalità creativa e sopravvivenza, consentendo creatività ','/images/minecraftCopertina.jpg',    ARRAY['minecraft/img1.jpg', 'minecraft/img2.jpg', 'minecraft/img3.jpg'],14.99);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'The last of Us','Adventure',2010,'"The Last of Us" è un videogioco d azione e avventura ambientato in un mondo post-apocalittico. I giocatori seguono la storia di Joel e Ellie mentre attraversano gli Stati Uniti devastati da uninfezione fungina che ha trasformato la popolazione.','/images/theLastOfUsCopertina.jpg' , ARRAY['theLastOfUs/img1.jpg', 'theLastOfUs/img2.jpg', 'theLastOfUs/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Destiny','Adventure',2015,'"Destiny" è un gioco sparatutto in prima persona sviluppato da Bungie. Ambientato in un universo futuristico e condiviso online, i giocatori assumono il ruolo di Guardiani, protettori dell umanità dotati di poteri speciali.  ','/images/destinyCopertina.jpg' , ARRAY['destiny/img1.jpg', 'destiny/img2.jpg', 'destiny/img3.jpg']);




insert into game (id,name,category,release_date,description,copertina,images,price) values (nextval('games_seq'),'Minecraft','Adventure',2008,'Minecraft è un videogioco sandbox in cui i giocatori possono esplorare mondi infiniti generati proceduralmente, raccogliere risorse, costruire strutture e sopravvivere contro mob ostili. Offre modalità creativa e sopravvivenza, consentendo creatività ','/images/minecraftCopertina.jpg',    ARRAY['minecraft/img1.jpg', 'minecraft/img2.jpg', 'minecraft/img3.jpg'],14.99);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'The last of Us','Adventure',2010,'"The Last of Us" è un videogioco d azione e avventura ambientato in un mondo post-apocalittico. I giocatori seguono la storia di Joel e Ellie mentre attraversano gli Stati Uniti devastati da uninfezione fungina che ha trasformato la popolazione.','/images/theLastOfUsCopertina.jpg' , ARRAY['theLastOfUs/img1.jpg', 'theLastOfUs/img2.jpg', 'theLastOfUs/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Destiny','Adventure',2015,'"Destiny" è un gioco sparatutto in prima persona sviluppato da Bungie. Ambientato in un universo futuristico e condiviso online, i giocatori assumono il ruolo di Guardiani, protettori dell umanità dotati di poteri speciali.  ','/images/destinyCopertina.jpg' , ARRAY['destiny/img1.jpg', 'destiny/img2.jpg', 'destiny/img3.jpg']);




insert into game (id,name,category,release_date,description,copertina,images,price) values (nextval('games_seq'),'Minecraft','Adventure',2008,'Minecraft è un videogioco sandbox in cui i giocatori possono esplorare mondi infiniti generati proceduralmente, raccogliere risorse, costruire strutture e sopravvivere contro mob ostili. Offre modalità creativa e sopravvivenza, consentendo creatività ','/images/minecraftCopertina.jpg',    ARRAY['minecraft/img1.jpg', 'minecraft/img2.jpg', 'minecraft/img3.jpg'],14.99);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'The last of Us','Adventure',2010,'"The Last of Us" è un videogioco d azione e avventura ambientato in un mondo post-apocalittico. I giocatori seguono la storia di Joel e Ellie mentre attraversano gli Stati Uniti devastati da uninfezione fungina che ha trasformato la popolazione.','/images/theLastOfUsCopertina.jpg' , ARRAY['theLastOfUs/img1.jpg', 'theLastOfUs/img2.jpg', 'theLastOfUs/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Destiny','Adventure',2015,'"Destiny" è un gioco sparatutto in prima persona sviluppato da Bungie. Ambientato in un universo futuristico e condiviso online, i giocatori assumono il ruolo di Guardiani, protettori dell umanità dotati di poteri speciali.  ','/images/destinyCopertina.jpg' , ARRAY['destiny/img1.jpg', 'destiny/img2.jpg', 'destiny/img3.jpg']);



/*action*/


insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Minecraft','Action',2008,'questa è la descrizione di minecraft','/images/minecraft.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'The last of Us','Action',2010,'questa è la descrizione di tlou','/images/theLastOfUs.jpg');
insert into game (id,name,category,release_date,description,copertina) values (nextval('games_seq'),'Destiny','Action',2015,'questa è la descrizione di destiny','/images/destiny.jpg');


/*strategy*/



/*fps*/


INSERT INTO utente (id,name) values (nextval('utente_seq'),'admin');
INSERT INTO credentials (id, email, password, role, user_id) VALUES (nextval('credentials_seq'),'admin@example.com', 'password', 'ADMIN', (SELECT id FROM utente where id = 1));
