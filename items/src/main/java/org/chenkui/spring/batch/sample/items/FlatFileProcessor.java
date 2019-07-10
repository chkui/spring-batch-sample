package org.chenkui.spring.batch.sample.items;

import org.chenkui.spring.batch.sample.entity.MaxTemperatureEntiry;
import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

//创建处理器
public class FlatFileProcessor {
	@Bean
	public ItemProcessor<WeatherEntity, MaxTemperatureEntiry> flatFileProcessor() {
		return new ItemProcessor<WeatherEntity, MaxTemperatureEntiry>() {
			@Override
			public MaxTemperatureEntiry process(WeatherEntity item) throws Exception {
				if (WeatherEntity.Type.TMAX.equals(item.getType())) { // 只输出最高温度的记录
					MaxTemperatureEntiry maxTemperatureEntiry = new MaxTemperatureEntiry();
					maxTemperatureEntiry.setSiteId(item.getSiteId());
					maxTemperatureEntiry.setDate(item.getMonth());
					maxTemperatureEntiry.setTemperature(item.getValue().toString());
					maxTemperatureEntiry.setType(item.getType().name());
					return maxTemperatureEntiry;
				} else {
					return null;
				}
			}
		};
	}
}