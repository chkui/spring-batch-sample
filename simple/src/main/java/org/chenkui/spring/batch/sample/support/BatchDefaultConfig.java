package org.chenkui.spring.batch.sample.support;

import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;

public class BatchDefaultConfig {
	@Bean
	//配置Step
	public Step simpleStep(StepBuilderFactory builder, ItemReader<Record> reader, ItemProcessor<Record, Msg> processor,
			ItemWriter<Msg> writer) {
		return builder.get("SimpleStep").<Record, Msg>chunk(10).reader(reader).processor(processor).writer(writer)
				.build();
	}

	@Bean
	//配置 Reader
	public ItemReader<Record> reader() {
		return new ItemReader<Record>() {
			private int count = 0;

			public Record read()
					throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				return ++this.count < 100 ? new Record().setId(this.count).setMsg("Read Number:" + this.count) : null;
			}
		};
	}

	@Bean
	//配置 Processor
	public ItemProcessor<Record, Msg> processor() {
		return new ItemProcessor<Record, Msg>() {
			public Msg process(Record item) throws Exception {
				return new Msg("MSG GET INFO = " + item.getMsg());
			}
		};
	}

	@Bean
	//配置 Writer
	public ItemWriter<Msg> writer() {
		return new ItemWriter<Msg>() {
			private int batchCount = 0;
			public void write(List<? extends Msg> items) throws Exception {
				System.out.println("Batch Count : " + ++batchCount + ". Data:");
				for (Msg msg : items) {
					System.out.println(msg.getInfo());
				}
			}
		};
	}
}
