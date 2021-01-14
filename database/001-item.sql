create table item
(
	sid int auto_increment
		primary key,
	scontent varchar(150) null comment '题干',
	sa varchar(100) not null comment 'A选项',
	sb varchar(100) not null comment 'B选项',
	sc varchar(100) null comment 'C选项',
	sd varchar(100) null comment 'D选项',
	ans varchar(100) null comment '试题答案'
)
comment '试题表';

