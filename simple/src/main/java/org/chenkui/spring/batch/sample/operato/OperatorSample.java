package org.chenkui.spring.batch.sample.operato;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
/**
 * 使用外部操作的方式启动
 * 
 * @author chenkui
 *
 */
public class OperatorSample {
	public static void main(String[] args) throws DuplicateJobException {
		ConfigurableApplicationContext ctx = SpringApplication.run(OperatorSample.class);
		Cron cron = ctx.getBean(Cron.class);
		cron.register();
	}
}

@Service
class Cron {
	@Autowired
	private JobOperator jobOperator;

	@Autowired
	private JobRegistry jobRegistry;

	@Autowired
	private JobFactory jobFactory;

	void register() throws DuplicateJobException {
		jobRegistry.register(jobFactory);
	}

	@Scheduled(cron = "0/2 * * * * ? ")
	void task1() {
		System.out.println("1");
		run();
	}

	private void run() {
		try {
			jobOperator.start("SimpleJob", "Builder=123,Timer=123");
		} catch (NoSuchJobException | JobInstanceAlreadyExistsException | JobParametersInvalidException e) {
			e.printStackTrace();
		}

		System.out.println("SUC");
	}
}
