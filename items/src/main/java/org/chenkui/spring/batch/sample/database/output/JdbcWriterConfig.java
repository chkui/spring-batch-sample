package org.chenkui.spring.batch.sample.database.output;

import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.chenkui.spring.batch.sample.items.JdbcWriter;
import org.chenkui.spring.batch.sample.support.SimpleReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//导入依赖配置
@Import({ SimpleReader.class, JdbcWriter.class })
/**
 * 批处理配置
 * 
 * @author chenkui
 *
 */
public class JdbcWriterConfig {
	@Bean
	// 配置Step
	public Step databaseWriterStep(StepBuilderFactory builder,
			@Qualifier("simpleReader") ItemReader<WeatherEntity> reader,
			@Qualifier("jdbcBatchWriter") ItemWriter<WeatherEntity> writer) {
		return builder.get("databaseWriterStep").<WeatherEntity, WeatherEntity>chunk(10).reader(reader).writer(writer).build();
	}

	@Bean
	public Job dataBaseWriteJob(@Qualifier("databaseWriterStep") Step step, JobBuilderFactory builder) {
		return builder.get("dataBaseWriteJob").start(step).build();
	}
}
