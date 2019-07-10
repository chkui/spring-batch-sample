package org.chenkui.spring.batch.sample.items;

import java.util.ArrayList;
import java.util.List;

import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

//随风溜达的向日葵 chkui.com
public class JdbcWriter {

	@Bean
	public ItemWriter<WeatherEntity> jdbcBatchWriter(JdbcTemplate template) {

		return new ItemWriter<WeatherEntity>() {
			final private static String INSERt_SQL = "INSERT INTO tmp_test_weather(siteid, month, type, value, ext) VALUES(?,?,?,?,?)";

			@Override
			public void write(List<? extends WeatherEntity> items) throws Exception {
				List<Object[]> batchArgs = new ArrayList<>();
				for (WeatherEntity entity : items) {
					Object[] objects = new Object[5];
					objects[0] = entity.getSiteId();
					objects[1] = entity.getMonth();
					objects[2] = entity.getType().name();
					objects[3] = entity.getValue();
					objects[4] = entity.getExt();
					batchArgs.add(objects);
				}
				template.batchUpdate(INSERt_SQL, batchArgs);
			}
		};
	}
}
