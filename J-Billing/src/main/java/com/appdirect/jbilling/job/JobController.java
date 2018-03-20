/**
 * 
 */
package com.appdirect.jbilling.job;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.jbilling.common.ResponseContainer;
import com.appdirect.jbilling.common.ResponseUtil;
import com.appdirect.jbilling.exceptions.JobExecutionException;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculatorService;

/**
 * @author rajesh
 *
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private PriceCalculatorService calculatorService;

	@PostMapping("/pricecalculator")
	public ResponseEntity<ResponseContainer> runPriceCalculator(@RequestParam("command") String command)
			throws InterruptedException, ExecutionException, JobExecutionException {

		if (command.equalsIgnoreCase("start")) {
			String strategy = "";
			JobDTO jobDTO = calculatorService.calculateIdealPrice(strategy);
			return ResponseUtil.getResponse(jobDTO, HttpStatus.ACCEPTED);
		}
		throw new JobExecutionException("Unknown Command for price calculator job");

	}

}
