create database jukebox;
use jukebox;
truncate table users;
alter table users modify userid int not null auto_increment;
create table users
(
userid int not null primary key auto_increment,
username varchar(50)not null,
password varchar(10)not null
);

create table songs
(
songid int not null primary key,
songname varchar(80) not null,
artistname varchar(30) not null,
artistid int not null,
albumname varchar(80) not null,
filepath varchar(1000) not null,
duration varchar(15) not null,
genre varchar(20) not null,
releasedate varchar(30)not null,
userid int not null
);
alter table songs
add foreign key(userid) references users(userid);

create table podcast
(
podcastid int not null primary key,
podcastname varchar(90)not null,
artistName varchar(80)not null,
artistId int not null,
genre varchar(70)not null 
);


create table podcastepisode
(
episodeid int not null ,
episodeno double not null primary key,
episodename varchar(100)not null,
filepath varchar(1000)not null,
releasedate varchar(30)not null,
artistId varchar(80) not null,
artistName varchar(80) not null,
genre varchar(70) not null,
podcastid int not null
);


alter table podcastepisode
add foreign key(podcastid) references podcast(podcastid);

create table playlist
(
id int not null primary key,
songid int not null,
songname varchar(100),
podcastplaylistname varchar(100),
podcastid int not null,
userid int not null
);


alter table playlist
add foreign key(podcastid) references podcast(podcastid);
alter table playlist
add foreign key(userid) references users(userid);

insert into users values(101,'john','abc@123');
-- insert into users values(102,'sam',7894561237,'khs@123');
-- insert into users values(103,'Divya',7418529637,'abc@173');
-- insert into users values(105,'Pooja',7897561231,'abc@1789');


select * from users;


insert into songs values(1111,'Ek Kahani','Gajendra Verma',1234,'Ek Kahani','C:\\Users\\Divya\\Downloads\\kahani.wav','3:25','Romantic song','22-11-2017',101);
insert into songs values(1112,'Raja ala','Avdhoot gupte',1235,'Pawankhind','C:\\Users\\Divya\\Downloads\\Raja-Aala-Pawankhind.wav','4:21','Marathi song','05-02-2022',101);
insert into songs values(1113,'Jai Ho','A.R.Rahman',1236,'Slumdog Millionaire','C:\\Users\\Divya\\Downloads\\13. Jai Ho.wav','3:00','Pop music','25-11-2008',101);
insert into songs values(1114,'Sadda haq','A.R.Rahman',1237,'Rockstar','C:\\Users\\Divya\\Downloads\\sadda haq.wav','6.49','Rock music','30-09-2011',101);
insert into songs values(1115,'Pasoori','Ali Sethi,Shae Gil',1238,'Pasoori','C:\\Users\\Divya\\Downloads\\Pasoori - Shae Gill.wav','4:37','Punjabi music','03-02-2022',101);
insert into songs values(1117,'All Izz Well','Sonu Nigam,Shan',1239,'3 Idiots','C:\\Users\\Divya\\Downloads\\Aal Izz Well 128 Kbps.wav','4:36','Pop music','03-05-2009',101);
insert into songs values(1118,'Tera Yaar Hoon Mai','Arijit singh',1240,'Sonu ki titu Ki Sweeti','C:\\Users\\Divya\\Downloads\\Tera Yaar Hoon Main  wave.wav','4:24','Indian music','26-02-2018',101);

select * from songs;
select * from songs where genre='pop music';
select distinct(artistname) from songs;
insert into podcast values(2221,'Study Motivation by Motivation2Study','Jack mas',4441,'Education');
insert into podcast values(2222,'Historychatter Podcast','Anirban Bandyopadhyay ',4442,'History');
insert into podcast values(2223,'Court junkie','Ashley Flowers,Brit Prawat',4443,'Crime');
insert into podcast values(2224,'Something Scary','Join Blair and Jed Shepherd',4445,'Horror');
insert into podcast values(2225,'Woice with Ankoor','Ankur Warikoo',4446,'Entrepreneurship');
insert into podcast values(2226,'The Inspiring Talk','Bijay Gautam',4447,'Inspirational');

select * from podcast;

insert into podcastepisode value(1,1.1,'Fialure Teaches You','C:\\Users\\Divya\\Downloads\\How-to-get-things-done.wav','02-03-20222',4441,'Jack mas','Education',2221);
insert into podcastepisode value(1,1.2,'How to get things done','C:\\Users\\Divya\\Downloads\\How-to-get-things-done.wav','06-04-20222',4441,'Jack mas','Education',2221);
insert into podcastepisode value(1,1.3,'Mind set of sucessfull People','C:\\Users\\Divya\\Downloads\\Mind-set-of-sucessfull-People.wav','26-10-20222',4441,'Jack mas','Education',2221);

insert into podcastepisode value(2,2.1,'The Harbour and the Cyclone,Bombay Born 1','C:\\Users\\Divya\\Downloads\\The_Harbour_and_the_Cyclone_Bombay_Born_1.wav','06-19-20222',4442,'Anirban Bandyopadhyay ','History',2222);
insert into podcastepisode value(2,2.2,'The Fort and the Maidan','C:\\Users\\Divya\\Downloads\\The_Fort_and_the_Maidan.wav','06-26-20222',4442,'Anirban Bandyopadhyay ','History',2222);
insert into podcastepisode value(2,2.3,'Bombay crash','C:\\Users\\Divya\\Downloads\\The_Crash_Of_1864-65_Bombay.wav','06-26-20222',4442,'Anirban Bandyopadhyay ','History',2222);

insert into podcastepisode value(3,3.1,'Kristine Bunch’s Nightmare','C:\\Users\\Divya\\Downloads\\Nightmare.wav','27-11-20222',4443,'Ashley Flowers,Brit Prawat','Crime',2223);
insert into podcastepisode value(3,3.2,'Kristine Bunch’s Struggle For Freedom','C:\\Users\\Divya\\Downloads\\Kristine-Bunch’s-Struggle-For-Freedom.wav','12-04-20216',4443,'Ashley Flowers,Brit Prawat','Crime',2223);
insert into podcastepisode value(3,3.3,' Expert Witnesses','C:\\Users\\Divya\\Downloads\\Expert-Witnesses.wav','12-12-2016',4443,'Ashley Flowers,Brit Prawat','Crime',2223);

insert into podcastepisode value(4,4.1,'Flicker','C:\\Users\\Divya\\Downloads\\fliker.wav','31-10-2022',4445,'Join Blair and Jed Shepherd','Horror',2224);
insert into podcastepisode value(4,4.2,'All Hallows Hell','C:\\Users\\Divya\\Downloads\\All-Hallows-Hell.wav','25-10-2022',4445,'Join Blair and Jed Shepherd','Horror',2224);
insert into podcastepisode value(4,4.3,'Creatures of the Night','C:\\Users\\Divya\\Downloads\\Creatures-of-the-Night.wav','18-10-2022',4445,'Join Blair and Jed Shepherd','Horror',2224);

insert into podcastepisode value(5,5.1,'Stocks and life','C:\\Users\\Divya\\Downloads\\Stocks-and-life.wav','27-10-2022',4446,'Ankur Warikoo','Entrepreneurship',2225);
insert into podcastepisode value(5,5.2,'Growth and Success','C:\\Users\\Divya\\Downloads\\Growth and Success.wav','22-09-2022',4446,'Ankur Warikoo','Entrepreneurship',2225);
insert into podcastepisode value(5,5.3,'My favorite question','C:\\Users\\Divya\\Downloads\\My favorite question.wav','13-10-2022',4446,'Ankur Warikoo','Entrepreneurship',2225);

insert into podcastepisode value(6,6.1,'The Best of TIT','C:\\Users\\Divya\\Downloads\\TIT_49_Best_of_TIT.wav','27-08-2018',4447,'Bijay Gautam','Inspirational',2226);
insert into podcastepisode value(6,6.2,'Believe in yourself','C:\\Users\\Divya\\Downloads\\TIT48_Believe_In-Yourself.wav','23-08-2018',4447,'Bijay Gautam','Inspirational',2226);
insert into podcastepisode value(6,6.3,'Bit The Habbit of journling','C:\\Users\\Downloads\\TIT-OFB.wav','19-10-2018',4447,'Bijay Gautam','Inspirational',2226);
update  podcastepisode set filepath='C:\\Users\\Divya\\sachi.wav' where episodeno=6.3;
select * from podcastepisode;

insert into playlist values(3331,1111,'Ek Kahani','Study Motivation by Motivation2Study',2221,101);
insert into playlist values(3332,1112,'Raja ala','Historychatter Podcast',2222,101);
insert into playlist values(3333,1113,'Jai Ho','Court junkie',2223,101);
insert into playlist values(3334,1114,'Sadda haq','Something Scary',2224,101);
insert into playlist values(3335,1115,'Pasoori','Woice of Ankoor',2225,101);
insert into playlist values(3336,1116,'All Izz Well','The Inspiring Talk',2226,101);

select * from playlist;

update  playlist set songname='Jai' where songname='ABC';
delete from playlist where id=3334;
