REPLACE INTO `roles` VALUES (1,'ADMIN');
REPLACE INTO `roles` VALUES (2,'USER');






insert into `genre` (id, name, imageurl) VALUES (1, "Country", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Country.jpg");
insert into `genre` (id, name, imageurl) VALUES (2, "Pop", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Pop.jpg");
insert into `genre` (id, name, imageurl) VALUES (3, "Rock", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Rock.jpg");
insert into `genre` (id, name, imageurl) VALUES (4, "Electronic", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Electronic.jpg");
insert into `genre` (id, name, imageurl) VALUES (5, "Hip Hop", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/HipHop.jpg");


insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (1, "Going With My Tractor", "Tim McGraw", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Tractor.jpg", 9.99, '1995-09-19', 1);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (2, "Traveler", "Chris Stapleton", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Traveller.jpg", 8.99, '2015-05-05', 1);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (3, "Thriller", "Michael Jackson", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Thriller.jpg", 15.99, '1982-11-30', 2);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (4, "Divide", "Ed Sheeran", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/divide.jpg" ,20.99, '2017-03-03', 2);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (5, "Abbey Road", "The Beatles", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/Abbey-Road.jpg", 12.99, '1969-09-26', 3);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (6, "Random Access Memories", "Daft Punk", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/RAM.jpg", 10.99, '2013-05-17', 4);
insert into `album` (id, name, artist, imageurl, price, release_date, genre_id) VALUES (7, "Take Care", "Drake", "https://hclcapstoneimages.s3.us-east-2.amazonaws.com/capstoneimages/take-care.jpg",16.99, '2011-11-15', 5);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (1, "My Tractor is Awesome", 4.33, 1, 1, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (2, "All I Want is a Life", 3.33, 2, 1, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (3, "Renegade", 2.33, 3, 1, 1);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (4, "Traveller (Single)", 6.99, 1, 2, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (5, "Nobody To Blame", 1.00, 2, 2, 1);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (6, "Parachute", 1.00, 3, 2, 1);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (7, "Thriller (Single)", 5.99, 1, 3, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (8, "Billie Jean", 4.00, 2, 3, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (9, "Beat It", 6.00, 3, 3, 2);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (10, "Castle on the Hill", 3.99, 1, 4, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (11, "Shape of You", 5.50, 2, 4, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (12, "Galway Girl", 4.50, 3, 4, 2);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (13, "Perfect", 7.00, 4, 4, 2);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (14, "Something", 5.00, 1, 5, 3);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (15, "Here Comes the Sun", 7.99, 2, 5, 3);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (16, "Get Lucky", 4.99, 1, 6, 4);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (17, "Lose Yourself to Dance", 4.00, 2, 6, 4);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (18, "Doin' it Right", 3.00, 3, 6, 4);

insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (19, "Marvins Room", 4.60, 1, 7, 5);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (20, "Headlines", 6.99, 2, 7, 5);
insert into `music` (id, name, price, track_number, album_id, genre_id) VALUES (21, "Make Me Proud", 5.40, 3, 7, 5);

insert into `users` (user_id,active,email,last_name,name,password,user_name) VALUES(1,1,"j@g.com","j","j","$2a$10$YMUUQKKVXjGxa4fAz0GQK.bbO/XAV6EROTAKFgNaAzmZ27M5D1A7W","username");
INSERT INTO `user_role`(user_id,role_id) VALUES(1,2);

insert into `users` (user_id,active,email,last_name,name,password,user_name) VALUES(2,1,"a@g.com","a","a","$2a$10$YMUUQKKVXjGxa4fAz0GQK.bbO/XAV6EROTAKFgNaAzmZ27M5D1A7W","admin");
INSERT INTO `user_role`(user_id,role_id) VALUES(2,2);

insert into `users` (user_id,active,email,last_name,name,password,user_name) VALUES(3,1,"b@g.com","b","b","$2a$10$YMUUQKKVXjGxa4fAz0GQK.bbO/XAV6EROTAKFgNaAzmZ27M5D1A7W","test123");
INSERT INTO `user_role`(user_id,role_id) VALUES(3,1);

