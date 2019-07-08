package org.chenkui.spring.batch.sample.commond;

import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
/**
 * 使用外部操作的方式启动
 * 
 * @author chenkui
 */
public class CommondSample {
	public static void main(String[] args) throws DuplicateJobException {
		//模拟测试参数, 这些参数值在执行Java时从外部传入的，比如-Dkey=value
		String[] argsExt = new String[2];
		argsExt[0] = "BuilderParam1=Value1";
		argsExt[1] = "BuilderParam2=Value2";
		//运行Spring Framework
		SpringApplication.run(CommondSample.class, argsExt);
	}
}
