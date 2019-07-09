package org.chenkui.spring.batch.sample.database;

import org.chenkui.spring.batch.sample.entity.MaxTemperatureEntiry;
import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.chenkui.spring.batch.sample.items.FlatFileProcessor;
import org.chenkui.spring.batch.sample.items.FlatFileReader;
import org.chenkui.spring.batch.sample.items.FlatFileWriter;
import org.chenkui.spring.batch.sample.items.JdbcReader;
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
@Import({ JdbcReader.class, SimpleProcessor.class, SimpleWriter.class })
/**
 * 批处理配置
 * 
 * @author chenkui
 *
 */
public class DataBaseReadAndWriteConfig {
	@Bean
	public Job simpleJob(@Qualifier("dataBaseOpsStep") Step step, JobBuilderFactory builder) {
		return builder.get("DatabaseOper").start(step).build();
	}

	@Bean
	// 配置Step
	public Step dataBaseOpsStep(StepBuilderFactory builder,
			@Qualifier("flatFileReader") ItemReader<WeatherEntity> reader,
			@Qualifier("simpleProcessor") ItemProcessor<WeatherEntity, MaxTemperatureEntiry> processor,
			@Qualifier("simpleWriter") ItemWriter<MaxTemperatureEntiry> writer) {
		return builder.get("SimpleStep").<WeatherEntity, MaxTemperatureEntiry>chunk(10).reader(reader)
				.processor(processor).writer(writer)
				// .faultTolerant().skipLimit(10).skip(Exception.class)
				.build();
	}
}
