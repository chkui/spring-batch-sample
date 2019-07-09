package org.chenkui.spring.batch.sample.database;

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
public class DataBaseItemApplication {
	public static void main(String[] args) throws DuplicateJobException {
		String[] argsExt = new String[1];
		argsExt[0] = "DataBaseItemApplication=12";
		SpringApplication.run(DataBaseItemApplication.class, argsExt);
	}
}
