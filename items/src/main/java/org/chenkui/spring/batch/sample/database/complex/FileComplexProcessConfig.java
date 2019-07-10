package org.chenkui.spring.batch.sample.database.complex;

import org.chenkui.spring.batch.sample.entity.MaxTemperatureEntiry;
import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.chenkui.spring.batch.sample.items.FlatFileReader;
import org.chenkui.spring.batch.sample.items.FlatFileWriter;
import org.chenkui.spring.batch.sample.items.JdbcReader;
import org.chenkui.spring.batch.sample.items.JdbcWriter;
import org.chenkui.spring.batch.sample.support.SimpleProcessor;
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
@Import({ FlatFileReader.class, JdbcWriter.class, JdbcReader.class, SimpleProcessor.class, FlatFileWriter.class })

//随风溜达的向日葵 chkui.com
public class FileComplexProcessConfig {
	@Bean
	// 配置Step1
	public Step file2DatabaseStep(StepBuilderFactory builder,
			@Qualifier("flatFileReader") ItemReader<WeatherEntity> reader,
			@Qualifier("jdbcBatchWriter") ItemWriter<WeatherEntity> writer) {
		return builder.get("file2DatabaseStep") // 创建
				.<WeatherEntity, WeatherEntity>chunk(50) // 分片
				.reader(reader) // 读取
				.writer(writer) // 写入
				.faultTolerant() // 开启容错处理
				.skipLimit(20) // 跳过设置
				.skip(Exception.class) // 跳过异常
				.build();
	}

	@Bean
	// 配置Step2
	public Step database2FileStep(StepBuilderFactory builder, 
			@Qualifier("jdbcCursorItemReader") ItemReader<WeatherEntity> reader,
			@Qualifier("simpleProcessor") ItemProcessor<WeatherEntity, MaxTemperatureEntiry> processor,
			@Qualifier("flatFileWriter") ItemWriter<MaxTemperatureEntiry> writer) {
		return builder.get("database2FileStep") // 创建
				.<WeatherEntity, MaxTemperatureEntiry>chunk(50) // 分片
				.reader(reader) // 读取
				.processor(processor) //
				.writer(writer) // 写入
				.faultTolerant() // 开启容错处理
				.skipLimit(20) // 跳过设置
				.skip(Exception.class) // 跳过异常
				.build();
	}

	@Bean
	public Job file2DatabaseJob(@Qualifier("file2DatabaseStep") Step step2Database,
			@Qualifier("database2FileStep") Step step2File, JobBuilderFactory builder) {
		return builder.get("complexDataJob").start(step2Database).next(step2File).build();
	}
}
