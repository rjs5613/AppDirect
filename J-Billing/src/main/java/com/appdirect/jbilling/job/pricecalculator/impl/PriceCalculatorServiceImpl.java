package com.appdirect.jbilling.job.pricecalculator.impl;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.appdirect.jbilling.exceptions.JobExecutionException;
import com.appdirect.jbilling.job.JobDTO;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculator;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculatorJob;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculatorService;

/**
 * 
 * @author rajesh
 *
 */
@Service
class PriceCalculatorServiceImpl implements PriceCalculatorService {

	@Autowired
	@Qualifier("IdealPrice")
	private PriceCalculatorJob idealPriceCalculatorJob;
	
	@Value("{idealprice.strategy}")
	private String strategy;

	@Override
	public JobDTO calculateIdealPrice(String strategy) throws InterruptedException, ExecutionException, JobExecutionException {
		
		if(StringUtils.isEmpty(strategy)){
			strategy = this.strategy;
		}

		if(!idealPriceCalculatorJob.isRunning()){
			PriceCalculator idealPriceCalculator = IdealPriceCalculators.getInstance(strategy);
			idealPriceCalculatorJob.start(idealPriceCalculator);
		}else{
			throw new JobExecutionException("Ideal Price Calculator Job is Already Running");
		}
		
		JobDTO dto = new JobDTO();
		dto.setJobName("Ideal Price Calculator");
		dto.setStarted(LocalDateTime.now());
		return dto;
	}
	
	

}
