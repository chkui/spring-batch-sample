package org.chenkui.spring.batch.sample.items;

import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

//创建读文件的Reader
public class FlatFileReader {
	// FeildSet的字段名，设置字段名之后可以直接使用名字作为索引获取数据。也可以使用索引位置来获取数据
	public final static String[] Tokenizer = new String[] { "siteId", "month", "type", "value", "ext" };
	private boolean userWrapper = false;

	@Bean
	//定义FieldSetMapper用于FieldSet->WeatherEntity
	public FieldSetMapper<WeatherEntity> fieldSetMapper() {
		return new FieldSetMapper<WeatherEntity>() {
			@Override
			public WeatherEntity mapFieldSet(FieldSet fieldSet) throws BindException {
				if (null == fieldSet) {
					return null; // fieldSet不存在则跳过该行处理
				} else {
					WeatherEntity observe = new WeatherEntity();
					observe.setSiteId(fieldSet.readRawString("siteId"));
					observe.setMonth(fieldSet.readRawString("month"));
					observe.setType(WeatherEntity.Type.valueOf(fieldSet.readRawString("type")));
					observe.setValue(Integer.valueOf(fieldSet.readRawString("value")));
					return observe;
				}
			}
		};
	}

	@Bean
	// 配置 Reader
	public ItemReader<WeatherEntity> flatFileReader(@Qualifier("fieldSetMapper") FieldSetMapper<WeatherEntity> fieldSetMapper) {
		FlatFileItemReader<WeatherEntity> reader = new FlatFileItemReader<>();
		reader.setResource(new FileSystemResource("src/main/resources/data.csv")); // 读取资源文件
		DefaultLineMapper<WeatherEntity> lineMapper = new DefaultLineMapper<>(); // 初始化 LineMapper实现类
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(); // 创建LineTokenizer接口实现，将字符串转换为FieldSet
		tokenizer.setNames(Tokenizer); // 设定每个字段的名称，如果不设置需要使用索引获取值
		lineMapper.setLineTokenizer(tokenizer); // 设置tokenizer工具

		if (userWrapper) { //使用 BeanWrapperFieldSetMapper 使用反射直接转换
			BeanWrapperFieldSetMapper<WeatherEntity> wrapperMapper = new BeanWrapperFieldSetMapper<>();
			wrapperMapper.setTargetType(WeatherEntity.class);
			fieldSetMapper = wrapperMapper;
		}

		lineMapper.setFieldSetMapper(fieldSetMapper);
		reader.setLineMapper(lineMapper);
		reader.setLinesToSkip(1); // 跳过的初始行，用于过滤字段行
		reader.open(new ExecutionContext());

		return reader;
	}
}
