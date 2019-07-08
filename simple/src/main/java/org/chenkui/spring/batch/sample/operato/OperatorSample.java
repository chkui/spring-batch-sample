package org.chenkui.spring.batch.sample.operato;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
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
		new SuspendThread().run(); //挂起系统一直运行
		ConfigurableApplicationContext ctx = SpringApplication.run(OperatorSample.class);
		Cron cron = ctx.getBean(Cron.class);
		cron.register(); //注册JobFactory
		cron.runJobLaunch();
	}
}

@Service
class Cron {
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	private JobOperator jobOperator;

	@Autowired
	private JobRegistry jobRegistry;

	@Autowired
	private JobFactory jobFactory;

	//注册JobFactory
	void register() throws DuplicateJobException {
		jobRegistry.register(jobFactory);
	}

	//使用JobLaunch执行
	void runJobLaunch() {
		Map<String, JobParameter> map = new HashMap<>();
		map.put("Builder", new JobParameter("1"));
		map.put("Timer", new JobParameter("2"));
		try {
			jobLauncher.run(jobFactory.createJob(), new JobParameters(map));
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "30 * * * * ? ")
	void task1() {
		System.out.println("1");
		runOperator();
	}

	//定时任务使用 JobOperator执行
	private void runOperator() {
		try {
			jobOperator.start("SimpleJob", "Builder=1,Timer=2");
		} catch (NoSuchJobException | JobInstanceAlreadyExistsException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
