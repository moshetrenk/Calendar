create database thecalendar;
use thecalendar;

create table friend (
username varchar(16) primary key,
userpassword varchar (16), 
imgUrl varchar(100));

create table theEvent (
eventNumber int primary key,
eventName varchar (25),
user_groupID varchar (16),
dateOfEvent varchar(10),
startTimeOfEvent time,
endTimeofEvent time,
description varchar(140),
approvalsNeeded int,
approved int);

create table calGroup (
idNum int primary key,
username varchar (16),
groupName varchar (16),
isAdmin bool);

create table notifications (
idNum int primary key,
sender varchar (16),
recipient varchar(16),
groupName varchar(16),
notificationType varchar(1)
);

insert friend values ('Moshe', 'password1', 'URL');
insert friend values ('Omar', 'password1', 'URL');
insert friend values ('Keith', 'password1', 'URL');
insert friend values ('Timothy', 'password1', 'URL');
insert friend values ('Chris', 'password1', 'URL');
insert friend values ('Tom', 'password1', 'URL');
insert friend values ('Dick', 'password1', 'URL');
insert friend values ('Harry', 'password1', 'URL');

insert theEvent values(1, 'Dr Appointment', 'Moshe', '08/06/2017', 183000, 200000, '', 1, 1);
insert theEvent values(2, 'CSC 330', 'Omar', '04/07/2016', 183000, 200000, 'Object Oriented Software Design - 1N 005', 1, 1);
insert theEvent values(3, 'B', 'Moshe', '04/07/2016', 131500, 140000, 'Therapuetic stuff', 1, 1);
insert theEvent values(4, 'Meeting Prof Jackson', 'Moshe', '04/22/2016', 83000, 84500, 'Meeting to discuss rampant homicides on campus', 1, 1);
insert theEvent values(5, 'Top Secret Classified', 'Keith', '04/07/2016', 183000, 200000, 'Look away, this shit is private', 1, 1);
insert theEvent values(6, 'Event1', 'CSC330 Project', '08/06/2017', 183000, 200000, '', 1, (select count(*) from calgroup where groupname = 'CSC330 Project'));
insert theEvent values(7, 'Event2', 'CSC330 Project', '08/06/2017', 183000, 200000, '', 1, (select count(*) from calgroup where groupname = 'CSC330 Project'));
insert theEvent values(8, 'Event3', 'Things', '08/06/2017', 183000, 200000, '', 1, (select count(*) from calgroup where groupname = 'Things'));
insert theEvent values(9, 'Event4', 'Other Stuff', '08/06/2017', 183000, 200000, '', 1, (select count(*) from calgroup where groupname = 'Other Stuff'));
insert theEvent values(10, 'Event5', 'Other Stuff', '08/06/2017', 183000, 200000, '', 1, (select count(*) from calgroup where groupname = 'Other Stuff'));
insert calGroup values(1, 'Moshe', 'CSC330 Project', 1);
insert calGroup values(2, 'Keith', 'CSC330 Project', 0);
insert calGroup values(3, 'Omar', 'CSC330 Project', 0);
insert calGroup values(4, 'Moshe', 'Things', 1);
insert calGroup values(5, 'Timothy', 'Things', 0);
insert calGroup values(6, 'Tom', 'Things', 0);
insert calGroup values(7, 'Dick', 'Things', 0);
insert calGroup values(8, 'Harry', 'Things', 0);
insert calgroup values(9, 'Moshe', 'Other Stuff', 1);

insert notifications values(1, 'Moshe', 'Tom', '' , 'F');
insert notifications values(2, 'Moshe', 'Harry', '',  'F');
insert notifications values(3, 'Moshe', 'Chris', 'Things', 'G');
insert notifications values(4, 'Moshe', 'Chris', 'Other Stuff', 'G');
insert notifications values(5, 'Moshe', 'Dick', 'Other Stuff', 'G');


-- select * from theevent where user_groupid = 'moshe';
-- delete from notifications ...

alter table notifications drop column notificationType;
alter table notifications drop column groupName;

alter table notifications add column groupName varchar(16) not null;

truncate notifications;

insert notifications values(1, 'Moshe', 'Tom', 'Things');
insert notifications values(2, 'Moshe', 'Harry', 'Things');
insert notifications values(3, 'Moshe', 'Chris', 'Things');
insert notifications values(4, 'Moshe', 'Chris', 'Other Stuff');
insert notifications values(5, 'Moshe', 'Dick', 'Other Stuff');

create table notificationsEvent (
idNum int primary key,
groupName varchar (16),
recipient varchar(16),
dateOfEvent varchar(16),
startTimeOfEvent time,
endTimeofEvent time);


 insert notificationsEvent values(1, 'CSC330 Project', 'Keith', '04/31/2016', 000000, 003000);
 insert notificationsEvent values(2, 'CSC330 Project', 'Omar', '04/31/2016', 000000, 003000);

alter table notifications rename notificationsGroup;




