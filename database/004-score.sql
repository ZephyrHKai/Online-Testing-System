create table score
(
	score_id int auto_increment comment '成绩表ID'
		primary key,
	user_id int not null,
	page_id int not null,
	total_score double not null,
	status int default 0 not null
)
comment '学生成绩表';

