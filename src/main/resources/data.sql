REPLACE INTO `roles` VALUES (1,'ADMIN');
REPLACE INTO `roles` VALUES (2,'USER');

ALTER TABLE `db_example`.`music` 
CHANGE COLUMN `id` `id` BIGINT NOT NULL AUTO_INCREMENT ;

ALTER TABLE `db_example`.`album` 
CHANGE COLUMN `id` `id` BIGINT NOT NULL AUTO_INCREMENT ;




insert into `genre` (id, name) VALUES (1, "Country");
insert into `genre` (id, name) VALUES (2, "Pop");
insert into `genre` (id, name) VALUES (3, "Rock");
insert into `genre` (id, name) VALUES (4, "Electronic");
insert into `genre` (id, name) VALUES (5, "Hip Hop");


insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (null, "Going With My Tractor", "Tim McGraw", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Tractor.jpg", 9.99, '1995-09-19', 1);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (null, "Traveler", "Chris Stapleton", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Traveller.jpg", 8.99, '2015-05-05', 1);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (null, "Thriller", "Michael Jackson", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Thriller.jpg", 15.99, '1982-11-30', 2);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (null, "Divide", "Ed Sheeran", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/divide.jpg" ,20.99, '2017-03-03', 2);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (null, "Abbey Road", "The Beatles", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Abbey-Road.jpg", 12.99, '1969-09-26', 3);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (null, "Random Access Memories", "Daft Punk", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/RAM.jpg", 10.99, '2013-05-17', 4);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (null, "Take Care", "Drake", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/take-care.jpg",16.99, '2011-11-15', 5);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "My Tractor is Awesome", 4.33, 1, 1, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "All I Want is a Life", 3.33, 2, 1, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Renegade", 2.33, 3, 1, 1);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Traveller (Single)", 6.99, 1, 2, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Nobody To Blame", 1.00, 2, 2, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Parachute", 1.00, 3, 2, 1);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Thriller (Single)", 5.99, 1, 3, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Billie Jean", 4.00, 2, 3, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Beat It", 6.00, 3, 3, 2);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Castle on the Hill", 3.99, 1, 4, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Shape of You", 5.50, 2, 4, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Galway Girl", 4.50, 3, 4, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Perfect", 7.00, 4, 4, 2);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Something", 5.00, 1, 5, 3);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Here Comes the Sun", 7.99, 2, 5, 3);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Get Lucky", 4.99, 1, 6, 4);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Lose Yourself to Dance", 4.00, 2, 6, 4);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Doin' it Right", 3.00, 3, 6, 4);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Marvins Room", 4.60, 1, 7, 5);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Headlines", 6.99, 2, 7, 5);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (null, "Make Me Proud", 5.40, 3, 7, 5);

insert into `users` (user_id,active,email,last_name,name,password,user_name) VALUES(1,1,"j@g.com","j","j","$2a$10$YMUUQKKVXjGxa4fAz0GQK.bbO/XAV6EROTAKFgNaAzmZ27M5D1A7W","username");
INSERT INTO `user_role`(user_id,role_id) VALUES(1,2);

insert into `users` (user_id,active,email,last_name,name,password,user_name) VALUES(2,1,"a@g.com","a","a","$2a$10$YMUUQKKVXjGxa4fAz0GQK.bbO/XAV6EROTAKFgNaAzmZ27M5D1A7W","admin");
INSERT INTO `user_role`(user_id,role_id) VALUES(2,2);

insert into `users` (user_id,active,email,last_name,name,password,user_name) VALUES(3,1,"b@g.com","b","b","$2a$10$YMUUQKKVXjGxa4fAz0GQK.bbO/XAV6EROTAKFgNaAzmZ27M5D1A7W","test123");
INSERT INTO `user_role`(user_id,role_id) VALUES(3,1);

