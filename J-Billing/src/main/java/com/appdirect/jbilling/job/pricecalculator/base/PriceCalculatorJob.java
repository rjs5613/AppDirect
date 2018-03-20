package com.appdirect.jbilling.job.pricecalculator.base;

import java.util.concurrent.ExecutionException;

import com.appdirect.jbilling.exceptions.JobExecutionException;

/**
 * 
 * @author rajesh
 *
 */
public interface PriceCalculatorJob {

	/**
	 * Calculates the Price of the product and saves in the DB based on the
	 * {@link PriceCalculator} Implementation
	 * 
	 * @param priceCalculator
	 * @throws JobExecutionException
	 *             if job is already running
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public void start(PriceCalculator priceCalculator)
			throws JobExecutionException, InterruptedException, ExecutionException;

	/**
	 * Checks if the Job is already Running
	 * 
	 * @return
	 */
	public boolean isRunning();

}
