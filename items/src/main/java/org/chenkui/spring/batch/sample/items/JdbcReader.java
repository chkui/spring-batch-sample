package org.chenkui.spring.batch.sample.items;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;

public class JdbcReader {

	@Bean
	public RowMapper<WeatherEntity> weatherEntityRowMapper() {

		return new RowMapper<WeatherEntity>() {
			public static final String SITEID_COLUMN = "siteId"; // 设置映射字段
			public static final String MONTH_COLUMN = "month";
			public static final String TYPE_COLUMN = "type";
			public static final String VALUE_COLUMN = "value";
			public static final String EXT_COLUMN = "ext";

			@Override
			// 数据转换
			public WeatherEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				WeatherEntity weatherEntity = new WeatherEntity();
				weatherEntity.setSiteId(resultSet.getString(SITEID_COLUMN));
				weatherEntity.setMonth(resultSet.getString(MONTH_COLUMN));
				weatherEntity.setType(WeatherEntity.Type.valueOf(resultSet.getString(TYPE_COLUMN)));
				weatherEntity.setValue(resultSet.getInt(VALUE_COLUMN));
				weatherEntity.setExt(resultSet.getString(EXT_COLUMN));
				return weatherEntity;
			}
		};
	}

	@Bean
	public ItemReader<WeatherEntity> jdbcCursorItemReader(
			@Qualifier("weatherEntityRowMapper") RowMapper<WeatherEntity> rowMapper, DataSource datasource) {
		JdbcCursorItemReader<WeatherEntity> itemReader = new JdbcCursorItemReader<>();
		itemReader.setDataSource(datasource); //设置DataSource
		itemReader.setSql("SELECT siteId, month, type, value, ext from TMP_TEST_WEATHER"); //设置读取的SQL
		itemReader.setRowMapper(rowMapper); //设置转换
		return itemReader;
	}
}
