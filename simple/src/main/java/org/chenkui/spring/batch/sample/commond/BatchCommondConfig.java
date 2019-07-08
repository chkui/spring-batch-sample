package org.chenkui.spring.batch.sample.commond;

import org.chenkui.spring.batch.sample.support.BatchDefaultConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BatchDefaultConfig.class })
/**
 * 批处理配置
 * 
 * @author chenkui
 *
 */
public class BatchCommondConfig {
	@Bean
	public Job simpleJob(Step step, JobBuilderFactory builder) {
		return builder.get("SimpleJob").start(step).build();
	}
}
