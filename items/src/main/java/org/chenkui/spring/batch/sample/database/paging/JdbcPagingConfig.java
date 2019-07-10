package org.chenkui.spring.batch.sample.database.paging;

import org.chenkui.spring.batch.sample.entity.MaxTemperatureEntiry;
import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.chenkui.spring.batch.sample.items.pageReader;
import org.chenkui.spring.batch.sample.support.SimpleProcessor;
import org.chenkui.spring.batch.sample.support.SimpleWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//导入依赖配置
@Import({ pageReader.class, SimpleProcessor.class, SimpleWriter.class })
/**
 * 批处理配置
 * 
 * @author chenkui
 *
 */
public class JdbcPagingConfig {
	@Bean
	// 配置Step
	public Step jdbcpagingStep(StepBuilderFactory builder,
			@Qualifier("jdbcPagingItemReader") ItemReader<WeatherEntity> reader,
			@Qualifier("simpleProcessor") ItemProcessor<WeatherEntity, MaxTemperatureEntiry> processor,
			@Qualifier("simpleWriter") ItemWriter<MaxTemperatureEntiry> writer) {
		return builder.get("jdbcpagingStep").<WeatherEntity, MaxTemperatureEntiry>chunk(10).reader(reader)
				.processor(processor)
				.writer(writer) //
				.faultTolerant() //扩展容错功能
				.skipLimit(10) //跳过设置
				.skip(Exception.class) //跳过的异常
				.build();
	}
	
	@Bean
	public Job jdbcpagingJob(@Qualifier("jdbcpagingStep") Step step, JobBuilderFactory builder) {
		return builder.get("jdbcpagingJob").start(step).build();
	}
}