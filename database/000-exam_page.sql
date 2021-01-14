create table exam_page
(
	exam_id int auto_increment
		primary key,
	user_id int not null comment '学生ID',
	sid int not null comment '题目ID',
	page_id int not null comment '试卷ID',
	student_key varchar(11) null comment '学生答案',
	s_key varchar(11) not null comment '试卷答案'
)
comment '学生试卷表';

