package com.appdirect.jbilling.job;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JobDTO {

	private String jobName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime started;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public LocalDateTime getStarted() {
		return started;
	}

	public void setStarted(LocalDateTime started) {
		this.started = started;
	}
	
	
}
