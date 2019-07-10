package org.chenkui.spring.batch.sample.database.complex;

import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing

//随风溜达的向日葵 chkui.com
public class FileComplexProcessApplication {
	public static void main(String[] args) throws DuplicateJobException {
		String[] argsExt = new String[1];
		argsExt[0] = "FileComplexProcessApplication=2";
		SpringApplication.run(FileComplexProcessApplication.class, argsExt);
	}
}
