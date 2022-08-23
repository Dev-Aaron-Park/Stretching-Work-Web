drop table stretchingwork_user cascade constraint purge;

drop table stretchingwork_work cascade constraint purge;

drop table stretchingwork_guestbook cascade constraint purge;

drop sequence stretchingwork_work_seq;

------------------------------------------------------------------------ SELECT

select * from stretchingwork_user;

select * from stretchingwork_work;

select * from stretchingwork_guestbook;

------------------------------------------------------------------------ SELECT

create sequence stretchingwork_guestbook_seq;

create sequence stretchingwork_work_seq;

create table stretchingwork_user(
	user_id varchar2(10 char) primary key,
	user_pw varchar2(10 char) not null,
	user_name varchar2(10 char) not null,
	user_birthday date not null,
	user_addr varchar2(100 char) not null,
	user_photo varchar2(150 char) not null
);

create table stretchingwork_work(
	work_no number(5) primary key, 
	work_id varchar2(10 char) not null,
	work_date date not null,
	work_todo varchar2(100 char) not null,
	work_memo varchar2(200 char),
	work_imp char(1 char) not null,
	constraint work_writer
		foreign key (work_id) references stretchingwork_user(user_id) on delete cascade
);

create table stretchingwork_guestbook(
	gb_no number(4) primary key,
	gb_id varchar2(10 char) not null,
	gb_title varchar2(40 char)not null,
	gb_contents varchar2(300 char)not null,
	gb_date date not null,
	constraint gb_writer
		foreign key (gb_id) references stretchingwork_user(user_id) on delete cascade
);

------------------------------------------------------------------------ TEST

constraint 제약조건명 foreign key (필드명) references 테이블명(필드명) on delete cascade

select * from NLS_DATABASE_PARAMETERS WHERE PARAMETER LIKE '%CHAR%';

select *
from (
	select rownum as rn, work_no, work_id, work_date, work_todo, work_memo, work_imp
	from (
		select work_no, work_id, work_date, work_todo, work_memo, work_imp
		from stretchingwork_work where work_id= ?
		order by work_date, work_imp, work_todo
	)
) where rn >= ? and rn <= ?

select *
from (
	select rownum as rn, user_id, user_photo, gb_title, gb_contents, gb_date 
	from (
		select user_id, user_photo, gb_title, gb_contents, gb_date 
		from stretchingwork_user, stretchingwork_guestbook 
		where user_id = gb_id 
		order by gb_date desc
	)
)
where rn >= 3 and rn <= 5;

select gb_no, user_pw from stretchingwork_guestbook, stretchingwork_user
where gb_id = user_id and gb_id = 'test2' and gb_no = 44;
