# spring-batch-sample
Some Use Case for Spring Batch 

代码仅用于演示效果，原理及介绍见：
* [数据批处理概念](https://www.chkui.com/article/spring/spring_batch_introduction)
* [Job配置与运行](https://www.chkui.com/article/spring/spring_batch_job)
* [Step控制](https://www.chkui.com/article/spring/spring_batch_step)
* [Item概念及使用代码](https://www.chkui.com/article/spring/spring_batch_item_and_code_case)
* [文件读写](https://www.chkui.com/article/spring/spring_batch_flat_file_read_and_write)
* [数据库读写](https://www.chkui.com/article/spring/spring_batch_database_read_and_write)

## 初始化数据库

Spring Batch需要有个数据库去存储执行过程中相关的元数据，运行之前请实现准备好数据库。并在每个子项目的`resources/application.yml`中配置好链接、账户和密码。

数据路的建表语句见：[Item概念及使用代码](https://www.chkui.com/article/spring/spring_batch_item_and_code_case)中**持久化数据**部分的说明。如果是Mysql,可参考[/ddl/mysql/create.sql](https://github.com/chkui/spring-batch-sample/blob/master/ddl/mysql/create.sql)。

## 子项目

### simple

用一个简单的例子说明*Spring Batch*的运行方式——命令行、JobLaunch和JobOperator。

### items

实现数据的各种读写过程。

**说明**

* 代码中的测试数据来自数据分析交流项目[bi-process-example](https://github.com/chkui/bi-process-example)，是NOAA的2015年全球天气监控数据。为了便于源码存储进行了大量的删减，原始数据有百万条，如有需要使用下列方式下载：
	```
	curl -O ftp://ftp.ncdc.noaa.gov/pub/data/ghcn/daily/by_year/2015.csv.gz #数据文件
	curl -O ftp://ftp.ncdc.noaa.gov/pub/data/ghcn/daily/ghcnd-stations.txt # 文件结构及类型说明
	```

* 代码实现了读取文件、处理文件、写入文件的整个过程。处理文件的过程是只获取监控的最高温度信息（`Type=TMAX`），其他都过滤。

* 本案例的包中有多个`main`方法用于不同的场景，详细内容见[文件读写](https://www.chkui.com/article/spring/spring_batch_flat_file_read_and_write)及[数据库读写](https://www.chkui.com/article/spring/spring_batch_database_read_and_write)的介绍。项目使用的是Command Runner的方式执行（运行方式的说明见[Item概念及使用代码](https://www.chkui.com/article/spring/spring_batch_item_and_code_case)的*命令行方式运行*、*Java内嵌运行*）。