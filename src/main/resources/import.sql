
/*recensioni*/



/*adventure*/


insert into game (id,name,category,release_date,description,copertina,images,price) values (nextval('games_seq'),'Minecraft','Adventure',2008,'Minecraft è un videogioco sandbox in cui i giocatori possono esplorare mondi infiniti generati proceduralmente, raccogliere risorse, costruire strutture e sopravvivere contro mob ostili. Offre modalità creativa e sopravvivenza, consentendo creatività ','/images/minecraftCopertina.jpg',    ARRAY['/images/minecraft/img1.jpg', 'minecraft/img2.jpg', 'minecraft/img3.jpg'],14.99);
-- Insert the review
INSERT INTO review (id,rating, text, game_id) VALUES (nextval('review_seq'),4, 'bello pisello', (SELECT id FROM game WHERE name = 'Minecraft' AND release_date = 2008));


insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'The last of Us','Adventure',2010,'"The Last of Us" è un videogioco d azione e avventura ambientato in un mondo post-apocalittico. I giocatori seguono la storia di Joel e Ellie mentre attraversano gli Stati Uniti devastati da uninfezione fungina che ha trasformato la popolazione.','/images/theLastOfUsCopertina.jpg' , ARRAY['/images/theLastOfUs/img1.jpg', 'theLastOfUs/img2.jpg', 'theLastOfUs/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Destiny','Adventure',2015,'"Destiny" è un gioco sparatutto in prima persona sviluppato da Bungie. Ambientato in un universo futuristico e condiviso online, i giocatori assumono il ruolo di Guardiani, protettori dell umanità dotati di poteri speciali.  ','/images/destinyCopertina.jpg' , ARRAY['/images/destiny/img1.jpg', 'destiny/img2.jpg', 'destiny/img3.jpg']);

insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Farcry','Adventure',2015,'descrizione','/images/farcryCopertina.jpg' , ARRAY['/images/farcry/img1.jpg', '/images/farcry/img2.jpg', '/images/farcry/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Fallout','Adventure',2015,'descrizione','/images/falloutCopertina.jpg' , ARRAY['/images/fallout/img1.jpg', '/images/fallout/img2.jpg', '/images/fallout/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Grand Theft Auto V','Adventure',2015,'descrizione','/images/gtaCopertina.jpg' , ARRAY['/images/gta/img1.jpg', '/images/gta/img2.jpg', '/images/gta/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Raft','Adventure',2015,'descrizione','/images/raftCopertina.jpg' , ARRAY['/images/raft/img1.jpg', '/images/raft/img2.jpg', '/images/raft/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Red Head Redemption','Adventure',2015,'descrizione','/images/redheadredemptionCopertina.jpg' , ARRAY['/images/redHeadRedemption/img1.jpg', '/images/redHeadRedemption/img2.jpg', '/images/redHeadRedemption/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Rust','Adventure',2015,'descrizione','/images/rustCopertina.jpg' , ARRAY['/images/rust/img1.jpg', '/images/rust/img2.jpg', '/images/rust/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Sons of The Forest','Adventure',2015,'descrizione','/images/sonsoftheforrestCopertina.jpg' , ARRAY['/images/sonsoftheforrest/img1.jpg', '/images/sonsoftheforrest/img2.jpg', '/images/sonsoftheforrest/img3.jpg']);




/*action*/



/*strategy*/


/*fps*/
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Counter-Strike 2','Fps',2012,'Per oltre due decenni, Counter-Strike ha offerto unesperienza competitiva di alto livello a cui milioni di giocatori da tutto il mondo hanno dato forma. Ora sta per iniziare il prossimo capitolo della storia di Counter-Strike: questo è Counter-Strike 2.','/images/counterStrike2Copertina.jpg' , ARRAY['/images/counterStrike2/img1.jpg', '/images/counterStrike2/img2.jpg', '/images/counterStrike2/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Destiny 2','Fps',2019,'Destiny 2 è un MMO d azione con un unico mondo in continua evoluzione, accessibile ovunque, gratuitamente e in qualsiasi momento con gli amici.','/images/destiny2Copertina.jpg' , ARRAY['/images/destiny2/img1.jpg', '/images/destiny2/img2.jpg', '/images/destiny2/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Rainbow Six® Siege','Fps',2015,'Tom Clancy s Rainbow Six® Siege è uno sparatutto tattico d élite a squadre, in cui trionfano pianificazione ed esecuzione ad alto livello.','/images/rainbowSixCopertina.jpg' , ARRAY['/images/rainbowSix/img1.jpg', '/images/rainbowSix/img2.jpg', '/images/rainbowSix/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Apex Legends™','Fps',2020,'Apex Legends è il pluripremiato battle royale gratuito sviluppato da Respawn Entertainment. Scopri un elenco in continua espansione di personaggi leggendari dotati di potenti abilità, scegli la strategia perfetta per la tua squadra.','/images/apexCopertina.jpg' , ARRAY['/images/apex/img1.jpg', '/images/apex/img2.jpg', '/images/apex/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Call of Duty®','Fps',2022,'Call of Duty® HQ supporta Call of Duty®: Black Ops 6, Call of Duty®: Modern Warfare® III, Call of Duty®: Modern Warfare® II e Call of Duty®: Warzone™.','/images/codCopertina.jpg' , ARRAY['/images/codb/img1.jpg', '/images/codb/img2.jpg', '/images/codb/img3.jpg']);
insert into game (id,name,category,release_date,description,copertina,images) values (nextval('games_seq'),'Halo','Fps',2019,'L avventura leggendaria di Master Chief include sei titoli, sviluppati per PC e raccolti in un unica esperienza integrata. ','/images/haloCopertina.jpg' , ARRAY['/images/halo/img1.jpg', '/images/halo/img2.jpg', '/images/halo/img3.jpg']);



INSERT INTO utente (id,name) values (nextval('utente_seq'),'admin');
INSERT INTO credentials (id, email, password, role, user_id) VALUES (nextval('credentials_seq'),'admin@example.com', 'p', 'ADMIN', (SELECT id FROM utente where id = 1));
