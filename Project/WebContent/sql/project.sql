create table likeboard 
(
bId number(4) primary key,
bUser varchar2(20)
);



create table mvc_board (
bId number(4) primary key,
bName varchar2(20),
bTitle varchar2(100),
bContent varchar2(300),
bDate date default sysdate,
bHit number(4) default 0,
bGroup number(4),
bStep number(4),
bIndent number(4),
bUser varchar2(20),
type varchar2(20)
);

create table filename
(
realname varchar2(20),
sysname varchar2(20),
bId number(4)
);


create table nc_board (
nId number(4) primary key,
nName varchar2(20),
nTitle varchar2(100),
nContent varchar2(300),
nDate date default sysdate,
nHit number(4) default 0,
nGroup number(4),
nStep number(4),
nIndent number(4),
nUser varchar2(20)
);

create table likeboard
(
bId number(4),
bUser varchar2(20)
);


create table members1 (

id varchar2(20) primary key,
pw varchar2(20),
name varchar2(20),
email varchar2(40),
rdate date,
address varchar2(40),
nickname varchar2(20),
manager varchar2(20),
ban varchar2(20),
mContent number(10),
mReply number(10),

);


create table boardcomment (

cnum number(4) primary key,
cboard number(4),
cid varchar2(20),
cnickname varchar2(20),
cdate date,
cparent number(4),
ccontent varchar2(1000),
CONSTRAINT FK_comment FOREIGN KEY(cboard) REFERENCES MVC_BOARD(Bid)
);

create sequence COMMENT_SEQ;


