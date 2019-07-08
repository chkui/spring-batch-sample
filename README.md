# spring-batch-sample
Some Use Case for Spring Batch 

代码仅用于演示效果，原理及介绍见：
* [数据批处理概念](https://www.chkui.com/article/spring/spring_batch_introduction)
* [Job配置与运行](https://www.chkui.com/article/spring/spring_batch_job)
* [Step控制](https://www.chkui.com/article/spring/spring_batch_step)
* [Item概念及使用代码](https://www.chkui.com/article/spring/spring_batch_item_and_code_case)

## 初始化数据库

Spring Batch需要有个数据库去存储执行过程中相关的元数据，运行之前请实现准备好数据库并在每个子项目的`resources/application.yml`配置链接、用户和密码。

## 结构

* **simple**用一个简单的例子说明*Spring Batch*的运行方式——命令行、JobLaunch和JobOperator。
