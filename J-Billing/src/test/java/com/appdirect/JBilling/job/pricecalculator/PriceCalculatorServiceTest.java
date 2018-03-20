package com.appdirect.JBilling.job.pricecalculator;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.appdirect.jbilling.JBillingApplication;
import com.appdirect.jbilling.exceptions.JobExecutionException;
import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.job.JobDTO;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculator;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculatorJob;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculatorService;
import com.appdirect.jbilling.job.pricecalculator.impl.IdealPriceCalculators;
import com.appdirect.jbilling.product.ProductPriceDTO;
import com.appdirect.jbilling.product.base.ProductService;

/**
 * 
 * @author rajesh
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JBillingApplication.class })
public class PriceCalculatorServiceTest {

	@Autowired
	private PriceCalculatorService calculatorService;

	@Autowired
	@Qualifier("IdealPrice")
	private PriceCalculatorJob calculatorJob;

	@Autowired
	private ProductService productService;

	@Test
	public void testCalculateIdealPrice() throws InterruptedException, ExecutionException, JobExecutionException {
		if (!calculatorJob.isRunning()) {
			JobDTO jobDTO = calculatorService.calculateIdealPrice("");
			Assert.assertTrue("Job Was not started", null != jobDTO);
		}

	}

	@Test
	@Ignore("Run only whe specific class is being Tested")
	public void testCalculateIdealPriceJob()
			throws InterruptedException, ExecutionException, JobExecutionException, NotFoundException {
		if (!calculatorJob.isRunning()) {
			PriceCalculator priceCalculator = IdealPriceCalculators.getInstance("sort");
			calculatorJob.start(priceCalculator);
			while (true) {
				if (!calculatorJob.isRunning()) {
					break;
				}
			}
			ProductPriceDTO productPrice = productService.getProductPriceInfo(2L);
			Assert.assertTrue("Product Price was not Calculated", null != productPrice);
		}

	}

	@Test(expected=NotFoundException.class)
	public void testProductPriceForException()
			throws InterruptedException, ExecutionException, JobExecutionException, NotFoundException {
		productService.getProductPriceInfo(200L);

	}

}
