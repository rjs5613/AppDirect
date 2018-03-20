package com.appdirect.jbilling.job.pricecalculator.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.appdirect.jbilling.exceptions.JobExecutionException;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculator;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculatorJob;
import com.appdirect.jbilling.product.ProductPriceDTO;
import com.appdirect.jbilling.product.base.ProductService;

/**
 * Ideal Price Calculator Job
 *
 * @author rajesh
 *
 */
@Component("IdealPrice")
class IdealPriceCalculatorJob implements PriceCalculatorJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(IdealPriceCalculatorJob.class);

	@Autowired
	private ProductService productService;

	private volatile Boolean isRunning = false;

	@Value("${idealprice.batchsize}")
	private int batchSize;

	/**
	 * Runs in Async Mode and Calculates Ideal Price based on
	 * {@link PriceCalculator} implementation
	 */
	@Async(value = "concurrentTaskExecutor")
	@Override
	public void start(PriceCalculator idealPriceCalculator)
			throws JobExecutionException, InterruptedException, ExecutionException {

		setupJob();
		int pageNumber = 0;
		List<Future<?>> futures = new ArrayList<>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		while (true) {
			LOGGER.debug("Page Number : " + pageNumber + "-- page Size : " + batchSize);
			List<ProductPriceDTO> priceDTOs = productService.getAggregatedPriceInfo(pageNumber, batchSize);
			if (priceDTOs.isEmpty()) {
				break;
			}
			Future<?> submit = executorService.submit(new Worker(priceDTOs, idealPriceCalculator));
			futures.add(submit);
			pageNumber++;
		}

		waitForCompletionAndEndJob(futures);

	}

	private void waitForCompletionAndEndJob(List<Future<?>> futures) throws InterruptedException, ExecutionException {
		int count = 0;
		for (Future<?> future : futures) {
			if (future.get() == null) {
				count++;
			}
		}
		if (count == futures.size()) {
			destroyJob();
		}
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	private void setupJob() throws JobExecutionException {
		synchronized (isRunning) {
			if (isRunning) {
				throw new JobExecutionException("Ideal Price Calculator Job is Already Running");
			}
			isRunning = true;
		}

	}

	private void destroyJob() {
		synchronized (isRunning) {
			isRunning = false;
		}
	}

	class Worker implements Runnable {

		private Collection<ProductPriceDTO> priceDTOs;
		private PriceCalculator priceCalculator;

		public Worker(Collection<ProductPriceDTO> priceDTOs, PriceCalculator priceCalculator) {
			this.priceDTOs = priceDTOs;
			this.priceCalculator = priceCalculator;
		}

		@Override
		public void run() {
			for (ProductPriceDTO priceDTO : priceDTOs) {
				priceCalculator.calculatePrice(priceDTO);
			}
			productService.savePriceInfos(priceDTOs);
		}

	}
}
