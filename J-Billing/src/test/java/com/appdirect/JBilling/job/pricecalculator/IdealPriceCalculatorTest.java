package com.appdirect.JBilling.job.pricecalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculator;
import com.appdirect.jbilling.job.pricecalculator.impl.IdealPriceCalculators;
import com.appdirect.jbilling.product.ProductPriceDTO;

public class IdealPriceCalculatorTest {
	
	private PriceCalculator priceCalculator;
	
	@Before
	public void setUp(){
		priceCalculator = IdealPriceCalculators.getInstance("");
	}
	
	@Test
	public void testForLessData(){
		
		BigDecimal a =new BigDecimal("10");
		ProductPriceDTO p = new ProductPriceDTO();
		p.setPrices(Arrays.asList(a));
		
		ProductPriceDTO calculatePrice = priceCalculator.calculatePrice(p);
		Assert.assertTrue(calculatePrice.getDescription().equals("Not Enough Data to calculate Ideal Price Accurately"));
		Assert.assertTrue(calculatePrice.getIdealPrice().doubleValue()==(a.doubleValue()));
	}
	
	@Test
	public void testForProperData(){
		
		List<BigDecimal> prices = new ArrayList<>();
		prices.add(new BigDecimal("10"));
		prices.add(new BigDecimal("20"));
		prices.add(new BigDecimal("30"));
		prices.add(new BigDecimal("40"));
		prices.add(new BigDecimal("60"));
		prices.add(new BigDecimal("60"));
		prices.add(new BigDecimal("70"));
		prices.add(new BigDecimal("80"));
		ProductPriceDTO p = new ProductPriceDTO();
		p.setPrices(prices);
		
		BigDecimal expectedResult = new BigDecimal("47.5");
		
		ProductPriceDTO calculatePrice = priceCalculator.calculatePrice(p);
		Assert.assertTrue(calculatePrice.getIdealPrice().doubleValue()==(expectedResult.doubleValue()));
	}
	

}
