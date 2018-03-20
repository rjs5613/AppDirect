package com.appdirect.JBilling.product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.appdirect.jbilling.JBillingApplication;
import com.appdirect.jbilling.exceptions.JobExecutionException;
import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculatorJob;
import com.appdirect.jbilling.job.pricecalculator.impl.IdealPriceCalculators;
import com.appdirect.jbilling.product.Product;
import com.appdirect.jbilling.product.ProductPriceDTO;
import com.appdirect.jbilling.product.base.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JBillingApplication.class })
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Autowired
	private PriceCalculatorJob calculatorJob;

	@Test
	public void testAggregateSortedPrices() {
		Collection<ProductPriceDTO> priceAggregates = productService.getAggregatedPriceInfo(0, 2);
		Assert.assertNotNull("Aggregates Can't be null", priceAggregates);
		for (ProductPriceDTO priceDTO : priceAggregates) {
			Assert.assertTrue("List Of Price of Product is not sorted", isSorted(priceDTO.getPrices()));
		}

	}

	private boolean isSorted(List<BigDecimal> prices) {
		for (int i = 0; i < prices.size() - 1; i++) {
			if (prices.get(i).compareTo(prices.get(i + 1)) > 0) {
				return false;
			}
		}
		return true;
	}

	@Test(expected = NotFoundException.class)
	public void testDeletePrices() throws NotFoundException {
		productService.deletePricingInfoOfProduct(Arrays.asList(100000L));
		productService.getProductPriceInfo(100000L);

	}

	@Test
	public void testProductSave() throws NotFoundException {
		Product p = new Product();
		p.setName("TestObject");
		p.setDescription("Item For Test");
		Product save = productService.save(p);
		Product product = productService.findById(save.getId());
		Assert.assertTrue("Product was not Saved", product != null);
	}

	@Test(expected = NotFoundException.class)
	public void testGetProductPriceInfoException() throws NotFoundException {
		productService.getProductPriceInfo(200L);
	}

	@Test
	public void testGetProductPriceInfo()
			throws NotFoundException, InterruptedException, ExecutionException, JobExecutionException {
		if (!calculatorJob.isRunning()) {
			calculatorJob.start(IdealPriceCalculators.getInstance(""));
			while (true) {
				if (!calculatorJob.isRunning()) {
					break;
				}
			}
			ProductPriceDTO productPriceInfo = productService.getProductPriceInfo(1L);
			Assert.assertTrue("Null Price info retrieved", null != productPriceInfo);
		}
	}

}
