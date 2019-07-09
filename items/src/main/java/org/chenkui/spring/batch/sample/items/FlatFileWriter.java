package org.chenkui.spring.batch.sample.items;

import org.chenkui.spring.batch.sample.entity.MaxTemperatureEntiry;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

//创建写文件的Writer
public class FlatFileWriter {

	private boolean useBuilder = true;

	@Bean
	public ItemWriter<MaxTemperatureEntiry> flatFileWriter() {
		BeanWrapperFieldExtractor<MaxTemperatureEntiry> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] { "siteId", "date", "temperature" }); //设置映射field
		fieldExtractor.afterPropertiesSet(); //参数检查

		DelimitedLineAggregator<MaxTemperatureEntiry> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(","); //设置输出分隔符
		lineAggregator.setFieldExtractor(fieldExtractor); //设置FieldExtractor处理器

		FlatFileItemWriter<MaxTemperatureEntiry> fileWriter = new FlatFileItemWriter<>();
		fileWriter.setLineAggregator(lineAggregator);
		fileWriter.setResource(new FileSystemResource("src/main/resources/out-data.csv")); //设置输出文件位置
		fileWriter.setName("outpufData");

		if (useBuilder) {//使用builder方式创建
			fileWriter = new FlatFileItemWriterBuilder<MaxTemperatureEntiry>().name("outpufData")
					.resource(new FileSystemResource("src/main/resources/out-data.csv")).lineAggregator(lineAggregator)
					.build();
		}
		return fileWriter;
	}
}
