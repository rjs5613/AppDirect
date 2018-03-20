package com.appdirect.jbilling.job.pricecalculator.base;

import java.util.concurrent.ExecutionException;

import com.appdirect.jbilling.exceptions.JobExecutionException;
import com.appdirect.jbilling.job.JobDTO;

/**
 * Price Calculator Service
 * 
 * @author rajesh
 *
 */
public interface PriceCalculatorService {

	/**
	 * Calculates Ideal Price of the Products based on strategy and saves them
	 * to DB
	 * 
	 * @param strategy
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws JobExecutionException
	 *             if Job is already Running
	 */
	JobDTO calculateIdealPrice(String strategy) throws InterruptedException, ExecutionException, JobExecutionException;

}
