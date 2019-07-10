package org.chenkui.spring.batch.sample.database.output;

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
public class JdbcWriterApplication {
	public static void main(String[] args) throws DuplicateJobException {
		String[] argsExt = new String[1];
		argsExt[0] = "JdbcWriterApplication=3";
		SpringApplication.run(JdbcWriterApplication.class, argsExt);
	}
}
