package org.chenkui.spring.batch.sample.operato;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobFactory;

public class SampleJobFactory implements JobFactory {

	private Job job;
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	@Override
	public Job createJob() {
		return job;
	}

	@Override
	public String getJobName() {
		return job.getName();
	}
}
