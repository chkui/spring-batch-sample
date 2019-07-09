package org.chenkui.spring.batch.sample.support;

import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;

public class SimpleReader {

	@Bean
	// 配置 Writer
	public ItemReader<WeatherEntity> simpleReader() {
		return new ItemReader<WeatherEntity>() {
			private final int totalCount = 500;
			private int readerCount = 0;

			@Override
			public WeatherEntity read()
					throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				if(totalCount > ++readerCount) {
					WeatherEntity entity = new WeatherEntity();
					entity.setSiteId("SiteID_" + readerCount);
					entity.setType(WeatherEntity.Type.TMAX);
					entity.setMonth("20190710");
					entity.setValue(readerCount);
					entity.setExt("Ext");
					return entity;
				}else {
					return null;
				}
			}
		};
	}
}
