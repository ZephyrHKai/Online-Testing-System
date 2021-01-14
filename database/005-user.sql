create table user
(
	user_id int auto_increment
		primary key,
	status int not null,
	username varchar(20) not null,
	userpwd varchar(20) not null,
	name varchar(20) null
)
comment '用户表';

