create table page_item
(
	`index` int auto_increment
		primary key,
	page_id int not null,
	sid int not null
)
comment '试卷-题目映射表';

