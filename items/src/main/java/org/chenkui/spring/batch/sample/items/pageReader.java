package org.chenkui.spring.batch.sample.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.chenkui.spring.batch.sample.entity.WeatherEntity;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;

//分页reader
public class pageReader {

	final private boolean wrapperBuilder = false;

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
	public SqlPagingQueryProviderFactoryBean queryProvider(DataSource dataSource) {
		SqlPagingQueryProviderFactoryBean provider = new SqlPagingQueryProviderFactoryBean();

		provider.setDataSource(dataSource);
		provider.setSelectClause("select id, siteid, month, type, value, ext");
		provider.setFromClause("from tmp_test_weather");
		provider.setWhereClause("where id>:start");
		provider.setSortKey("id");

		return provider;
	}

	@Bean
	public ItemReader<WeatherEntity> jdbcPagingItemReader(DataSource dataSource, PagingQueryProvider queryProvider,
			RowMapper<WeatherEntity> rowMapper) {
		Map<String, Object> parameterValues = new HashMap<>();
		parameterValues.put("start", "1");

		JdbcPagingItemReader<WeatherEntity> itemReader;
		if (wrapperBuilder) {
			itemReader = new JdbcPagingItemReaderBuilder<WeatherEntity>().name("creditReader").dataSource(dataSource)
					.queryProvider(queryProvider).parameterValues(parameterValues).rowMapper(rowMapper).pageSize(1000)
					.build();
		} else {
			itemReader = new JdbcPagingItemReader<>();
			itemReader.setName("weatherEntityJdbcPagingItemReader");
			itemReader.setDataSource(dataSource);
			itemReader.setQueryProvider(queryProvider);
			itemReader.setParameterValues(parameterValues);
			itemReader.setRowMapper(rowMapper);
			itemReader.setPageSize(1000);
		}	
		return itemReader;
	}
}
