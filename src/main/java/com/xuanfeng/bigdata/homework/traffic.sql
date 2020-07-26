流量统计
domain           time         traffic
gifshow.com      2020/01/01     5
yy.com           2020/01/01     4
huya.com         2020/01/01     1
gifshow.com      2020/01/20     6
gifshow.com      2020/02/01     8
yy.com           2020/01/20     5
gifshow.com      2020/02/02     7
==>
domain     		 time     cnt     sum
gifshow.com    2020-01    11       11
gifshow.com    2020-02    15       26
yy.com         2020-01    9         9
huya.com       2020-01    1         1

--------------------->
step0 造数据
MockData.java
    ==>mockTraffic()

step1 建表
create table if not exists traffic (
    domain string,
    time string,
    traffic int
)
row format delimited fields terminated by ','
;

step2 导入数据
load data local inpath '/home/hadoop/data/traffic.csv' overwrite into table traffic;

step3 查询
select a.domain, a.time, a.traffic
,sum(b.traffic) as traffic
from (select domain
        ,concat(substr(time,1,4),"-",substr(time,6,2)) as time
        ,sum(traffic) as traffic
from traffic
group by domain,concat(substr(time,1,4),"-",substr(time,6,2))
)a
join
(select domain
        ,concat(substr(time,1,4),"-",substr(time,6,2)) as time
        ,sum(traffic) as traffic
from traffic
group by domain,concat(substr(time,1,4),"-",substr(time,6,2))
)b
on a.domain=b.domain
where a.time>=b.time
group by a.domain,a.time,a.traffic
;




