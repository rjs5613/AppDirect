package com.appdirect.jbilling.job;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.appdirect.jbilling.exceptions.JobExecutionException;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculatorService;

/**
 * Task Schedulers
 * 
 * @author rajesh
 *
 */
@Component
public class ScheduledTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

	@Autowired
	private PriceCalculatorService priceCalculatorService;

	@Value("${idealprice.strategy}")
	private String strategy;

	@Value("${idealprice.enablejob}")
	private boolean isEnabled;

	/**
	 * Schedules Ideal Price Calculator Job
	 */
	@Scheduled(fixedRate = 30000)
	public void scheduledIdealPriceCalculatorJob() {
		if (isEnabled) {
			try {
				priceCalculatorService.calculateIdealPrice(strategy);
			} catch (InterruptedException | ExecutionException | JobExecutionException e) {
				LOGGER.error("Ideal Price Scheduled Job Failed ", e);
			}
		}

	}

}
