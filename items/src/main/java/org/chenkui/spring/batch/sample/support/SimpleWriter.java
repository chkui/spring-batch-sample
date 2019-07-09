package org.chenkui.spring.batch.sample.support;

import java.util.List;

import org.chenkui.spring.batch.sample.entity.MaxTemperatureEntiry;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;

public class SimpleWriter {
	@Bean
	// 配置 Writer
	public ItemWriter<MaxTemperatureEntiry> simpleWriter() {
		return new ItemWriter<MaxTemperatureEntiry>() {
			private int batchCount = 0;

			public void write(List<? extends MaxTemperatureEntiry> items) throws Exception {
				System.out.println("Batch Count : " + ++batchCount + ". Data:");
				for (MaxTemperatureEntiry item : items) {
					System.out.println(item);
				}
			}
		};
	}
}
