REPLACE INTO `roles` VALUES (1,'ADMIN');
REPLACE INTO `roles` VALUES (2,'USER');

insert into `genre` (id, name) VALUES (1, "Country");
insert into `genre` (id, name) VALUES (2, "Pop");
insert into `album` (id, name, artist, price, release_date, genre_id) VALUES (1, "Going With My Tractor", "Tim McGraw", 9.99, '2020-01-01', 1);
insert into `album` (id, name, artist, price, release_date, genre_id) VALUES (2, "Traveler", "Chris Stapleton", 9.99, '2020-01-01', 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (1, "My Tractor is Awesome", 0.99, 1, 1, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (2, "Tractor 2", 6.99, 2, 1, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (3, "Country Song", 2.99, 3, 1, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (4, "Starting Over", 0.99, 1, 2, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (5, "Over", 5.99, 2, 2, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (6, "B", 3.99, 3, 2, 2);

insert into `users` (user_id,active,email,last_name,name,password,user_name) VALUES(1,1,"j@g.com","j","j","$2a$10$YMUUQKKVXjGxa4fAz0GQK.bbO/XAV6EROTAKFgNaAzmZ27M5D1A7W","username");
INSERT INTO `user_role`(user_id,role_id) VALUES(1,2);