package org.chenkui.spring.batch.sample.support;

import org.chenkui.spring.batch.sample.entity.MaxTemperatureEntiry;
import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

public class SimpleProcessor {
	@Bean
	// 配置 Processor
	public ItemProcessor<WeatherEntity, MaxTemperatureEntiry> simpleProcessor() {
		return new ItemProcessor<WeatherEntity, MaxTemperatureEntiry>() {
			private int count = 0;
			public MaxTemperatureEntiry process(WeatherEntity item) throws Exception {
				if (WeatherEntity.Type.TMAX.equals(item.getType())) {
					MaxTemperatureEntiry maxTemperatureEntiry = new MaxTemperatureEntiry();
					maxTemperatureEntiry.setSiteId(item.getSiteId());
					maxTemperatureEntiry.setDate(item.getMonth());
					maxTemperatureEntiry.setTemperature(item.getValue().toString());
					maxTemperatureEntiry.setType(item.getType().name());
					
					System.out.println("" + ++count + ":" + maxTemperatureEntiry);
					
					return maxTemperatureEntiry;
				} else {
					return null;
				}
			}
		};
	}
}
