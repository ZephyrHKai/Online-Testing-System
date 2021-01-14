create table page
(
	page_id int auto_increment comment '试卷ID'
		primary key,
	page_name varchar(11) not null comment '试卷名',
	status int default 0 not null comment '试卷状态(0-不可用，1-可用)',
	total int not null comment '试题数'
)
comment '试卷表';

