/**
 * 
 */
package com.appdirect.jbilling.job.pricecalculator.base;

import com.appdirect.jbilling.product.ProductPriceDTO;

/**
 * @author rajesh
 *
 */
public interface PriceCalculator {
	
	public ProductPriceDTO calculatePrice(ProductPriceDTO priceDTO);

}
