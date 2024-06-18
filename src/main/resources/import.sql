
/*recensioni*/



/*adventure*/


insert into game (id,name,category,release_date,description,copertina,images,price) values (nextval('games_seq'),'Minecraft','Adventure',2008,'Minecraft è un videogioco sandbox in cui i giocatori possono esplorare mondi infiniti generati proceduralmente, raccogliere risorse, costruire strutture e sopravvivere contro mob ostili. Offre modalità creativa e sopravvivenza, consentendo creatività ','/images/minecraftCopertina.jpg',    ARRAY['minecraft/img1.jpg', 'minecraft/img2.jpg', 'minecraft/img3.jpg'],14.99);
-- Insert the review
INSERT INTO review (id,rating, text, game_id) VALUES (nextval('review_seq'),4, 'bello pisello', (SELECT id FROM game WHERE name = 'Minecraft' AND release_date = 2008));


insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'The last of Us','Adventure',2010,'"The Last of Us" è un videogioco d azione e avventura ambientato in un mondo post-apocalittico. I giocatori seguono la storia di Joel e Ellie mentre attraversano gli Stati Uniti devastati da uninfezione fungina che ha trasformato la popolazione.','/images/theLastOfUsCopertina.jpg' , ARRAY['theLastOfUs/img1.jpg', 'theLastOfUs/img2.jpg', 'theLastOfUs/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Destiny','Adventure',2015,'"Destiny" è un gioco sparatutto in prima persona sviluppato da Bungie. Ambientato in un universo futuristico e condiviso online, i giocatori assumono il ruolo di Guardiani, protettori dell umanità dotati di poteri speciali.  ','/images/destinyCopertina.jpg' , ARRAY['destiny/img1.jpg', 'destiny/img2.jpg', 'destiny/img3.jpg']);

insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Farcry','Adventure',2015,'descrizione','/images/farcryCopertina.jpg' , ARRAY['farcry/img1.jpg', 'farcry/img2.jpg', 'farcry/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Fallout','Adventure',2015,'descrizione','/images/falloutCopertina.jpg' , ARRAY['fallout/img1.jpg', 'fallout/img2.jpg', 'fallout/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Grand Theft Auto V','Adventure',2015,'descrizione','/images/gtaCopertina.jpg' , ARRAY['gta/img1.jpg', 'gta/img2.jpg', 'gta/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Raft','Adventure',2015,'descrizione','/images/raftCopertina.jpg' , ARRAY['raft/img1.jpg', 'raft/img2.jpg', 'raft/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Red Head Redemption','Adventure',2015,'descrizione','/images/redheadredemptionCopertina.jpg' , ARRAY['redHeadRedemption/img1.jpg', 'redHeadRedemption/img2.jpg', 'redHeadRedemption/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Rust','Adventure',2015,'descrizione','/images/rustCopertina.jpg' , ARRAY['rust/img1.jpg', 'rust/img2.jpg', 'rust/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Sons of The Forest','Adventure',2015,'descrizione','/images/sonsoftheforrestCopertina.jpg' , ARRAY['sonsoftheforrest/img1.jpg', 'sonsoftheforrest/img2.jpg', 'sonsoftheforrest/img3.jpg']);




/*action*/



/*strategy*/



/*fps*/






INSERT INTO utente (id,name) values (nextval('utente_seq'),'admin');
INSERT INTO credentials (id, email, password, role, user_id) VALUES (nextval('credentials_seq'),'admin@example.com', 'password', 'ADMIN', (SELECT id FROM utente where id = 1));
