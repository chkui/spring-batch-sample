package org.chenkui.spring.batch.sample.flatfile;

import java.util.List;

import org.chenkui.spring.batch.sample.entity.MaxTemperatureEntiry;
import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.chenkui.spring.batch.sample.items.FlatFileProcessor;
import org.chenkui.spring.batch.sample.items.FlatFileReader;
import org.chenkui.spring.batch.sample.items.FlatFileWriter;
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
@Import({ FlatFileReader.class, FlatFileProcessor.class, FlatFileWriter.class })
/**
 * 批处理配置
 * 
 * @author chenkui
 *
 */
public class FlatFileReadAndWrteConfig {
	@Bean
	public Job simpleJob(@Qualifier("flatfileOpsStep") Step step, JobBuilderFactory builder) {
		return builder.get("FlatFileOper").start(step).build();
	}

	@Bean
	// 配置Step
	public Step flatfileOpsStep(StepBuilderFactory builder,
			@Qualifier("flatFileReader") ItemReader<WeatherEntity> reader,
			@Qualifier("flatFileProcessor") ItemProcessor<WeatherEntity, MaxTemperatureEntiry> processor,
			@Qualifier("flatFileWriter") ItemWriter<MaxTemperatureEntiry> writer) {
		return builder.get("SimpleStep").<WeatherEntity, MaxTemperatureEntiry>chunk(10).reader(reader)
				.processor(processor).writer(writer)
				// .faultTolerant().skipLimit(10).skip(Exception.class)
				.build();
	}

	@Bean
	// 配置 Processor
	public ItemProcessor<WeatherEntity, String> processor() {
		return new ItemProcessor<WeatherEntity, String>() {
			public String process(WeatherEntity item) throws Exception {
				return item.toString();
			}
		};
	}

	@Bean
	// 配置 Writer
	public ItemWriter<String> writer() {
		return new ItemWriter<String>() {
			private int batchCount = 0;

			public void write(List<? extends String> items) throws Exception {
				System.out.println("Batch Count : " + ++batchCount + ". Data:");
				for (String msg : items) {
					System.out.println(msg);
				}
			}
		};
	}
}
