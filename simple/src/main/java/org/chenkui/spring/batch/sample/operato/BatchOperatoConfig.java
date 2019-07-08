package org.chenkui.spring.batch.sample.operato;

import org.chenkui.spring.batch.sample.support.BatchDefaultConfig;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BatchDefaultConfig.class})
/**
 * 批处理配置
 * 
 * @author chenkui
 *
 */
public class BatchOperatoConfig {
	@Bean
	public JobFactory simpleJob(Step step, JobBuilderFactory builder) {
		SampleJobFactory sampleJobFactory = new SampleJobFactory();
		sampleJobFactory.setJob(builder.get("SimpleJob").start(step).build());
		return sampleJobFactory;
	}
}
