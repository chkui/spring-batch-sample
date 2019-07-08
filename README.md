# spring-batch-sample
Some Use Case for Spring Batch 

代码仅用于演示效果，原理及介绍见：
* [数据批处理概念](https://www.chkui.com/article/spring/spring_batch_introduction)
* [Job配置与运行](https://www.chkui.com/article/spring/spring_batch_job)
* [Step控制](https://www.chkui.com/article/spring/spring_batch_step)
* [Item概念及使用代码](https://www.chkui.com/article/spring/spring_batch_item_and_code_case)

## 初始化数据库

Spring Batch需要有个数据库去存储执行过程中相关的元数据，运行之前请实现准备好数据库。并在每个子项目的`resources/application.yml`中配置好链接、账户和密码。

数据路的建表语句见：[Item概念及使用代码](https://www.chkui.com/article/spring/spring_batch_item_and_code_case)中**持久化数据**部分的说明。如果是Mysql,可参考[/ddl/mysql/create.sql](https://github.com/chkui/spring-batch-sample/blob/master/ddl/mysql/create.sql)。

## 子项目

* **simple**用一个简单的例子说明*Spring Batch*的运行方式——命令行、JobLaunch和JobOperator。