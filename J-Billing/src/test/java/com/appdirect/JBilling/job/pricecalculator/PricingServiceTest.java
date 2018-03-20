package com.appdirect.JBilling.job.pricecalculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.appdirect.jbilling.JBillingApplication;
import com.appdirect.jbilling.pricing.base.PricingService;

/**
 * 
 * @author rajesh
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JBillingApplication.class})
public class PricingServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PricingServiceTest.class); 

	@Autowired
	private PricingService pricingService;

	@Test
	public void testGetProductPriceMap() {
		LOGGER.debug("Starting Test Product Price Map");
		Map<Long, List<BigDecimal>> productPriceMap = pricingService.getProductPriceMap(Arrays.asList(1L));
		Assert.assertTrue("Product Price Map Doesn't have Id: " + 1L, null != productPriceMap.get(1L));
	}

}
