truncate table users cascade;
truncate table media cascade;

insert into users (id,email,password,time_created) values
(200,'john@gmail.com','password','2024-06-04T15:03:03.79200097'),
 (201,'john1@gmail.com','password','2024-06-04T15:03:03.70200097'),
(202,'john2@gmail.com','password','2024-06-04T15:03:03.70200097'),
(203,'john3@gmail.com','password','2024-06-04T15:03:03.70200097');

insert into media (id,category,description,url,time_created,user_id) values
(100,'ACTION','media 1','https://www.cloudinary.com/media1','2024-06-04T15:03:03.79200097',200),
(101,'HORROR','media 2','https://www.cloudinary.com/media2','2024-06-04T15:03:03.79200097',201),
(102,'ROMANCE','media 3','https://www.cloudinary.com/media3','2024-06-04T15:03:03.79200097',202),
(103,'COMEDY','media 4','https://www.cloudinary.com/media4','2024-06-04T15:03:03.79200097',203),
(104,'HORROR','media 5','https://www.cloudinary.com/media5','2024-06-04T15:03:03.79200097',201);

