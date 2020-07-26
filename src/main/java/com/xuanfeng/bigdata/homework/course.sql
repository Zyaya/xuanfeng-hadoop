
统计最热门的课程Top10
http://ruozedata.com/course/458655.html      <= Spark实战
http://ruozedata.com/course/458655/2.html?a=b&c=d    <= Spark Core01
                                              458655_2

------------->
step0 造数据
MockData.java
    ==>mockCourse()

step1 建表
create table if not exists course (
    url string
)
row format delimited fields terminated by '\t'
;

step2 导数
load data local inpath '/home/hadoop/data/course.csv' overwrite into table course;

step3 add jar
add jar /home/hadoop/lib/xuanfeng-hadoop-1.0.jar

step4 临时函数
create temporary function url_parse as "com.xuanfeng.bigdata.homework.CourseIdParseUDF";

step5 查询
select url_parse(url) as url,count(1) as pv from course group by url_parse(url) order by pv desc limit 10;



