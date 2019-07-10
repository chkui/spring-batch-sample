package org.chenkui.spring.batch.sample.database.cursor;

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
public class JdbcCurosrApplication {
	public static void main(String[] args) throws DuplicateJobException {
		String[] argsExt = new String[1];
		argsExt[0] = "JdbcCurosrApplication=1";
		SpringApplication.run(JdbcCurosrApplication.class, argsExt);
	}
}
