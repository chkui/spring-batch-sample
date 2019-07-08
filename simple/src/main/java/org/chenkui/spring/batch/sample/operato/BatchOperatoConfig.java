package org.chenkui.spring.batch.sample.operato;

import org.chenkui.spring.batch.sample.support.BatchDefaultConfig;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//导入进出配置 
@Import({ BatchDefaultConfig.class })
/**
 * 批处理配置
 * 
 * @author chenkui
 *
 */
public class BatchOperatoConfig {
	@Bean
	// 返回JobFactory
	public JobFactory simpleJob(Step step, JobBuilderFactory builder) {
		SimpleJobFactory sampleJobFactory = new SimpleJobFactory();
		sampleJobFactory.setJob(builder.get("SimpleJob").start(step).build());
		return sampleJobFactory;
	}
}
