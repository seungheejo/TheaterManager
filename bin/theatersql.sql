commit;

CREATE table theatermember(
memberNum number not null,
name varchar2(15) not null,
grade varchar2(10) not null,
email varchar2(30) not null,
phoneNum varchar2(20) not null,
id varchar2(15) constraint theatermember_memberNum_pk primary key,
pw varchar2(15) not null,
phoneCompany varchar2(5) not null,
jumin varchar2(20) not null,
points number default 0

);

CREATE SEQUENCE theatermember_seq;

CREATE table nonmember(
email varchar2(30) constraint nonmember_email_pk primary key,
name varchar2(15) not null,
phoneNum number

);

CREATE table reservation(
reserveNum number constraint reservation_reserveNum_pk primary key,
reserveDate date default sysdate,
movieTitle varchar2(100) constraint movie_movieTitle_fk references movie,
theaterName varchar2(20) constraint theater1_theaterName_fk references theater,
chosenSeat varchar2(100) not null,
ticketPrice number default 0, 
id varchar2(15) constraint theatermember_id_fk references theatermember,
email varchar2(30) not null
);

CREATE SEQUENCE reserveNum_seq;


CREATE table movie(
movieTitle varchar2(100) constraint movie_movieTitle_pk primary key,
rating varchar2(30) not null,
theaterName varchar2(20) constraint theater_theaterName_fk references theater,
genre varchar2(20) not null,
company varchar2(20) not null,
releaseDate date
);

CREATE table theater(
theaterName varchar2(20) constraint theater_theaterName_pk primary key,
showingRoomSeatsNum number
);

CREATE table seatinfo(
theaterName varchar2(20) constraint seatinfo_seats_pk primary key,
seats varchar2(10) constraint seatinfo_seats_pk primary key,
bookingInfo varchar2(20) not null
);