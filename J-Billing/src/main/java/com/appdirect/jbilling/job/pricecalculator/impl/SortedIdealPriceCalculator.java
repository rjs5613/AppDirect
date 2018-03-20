/**
 * 
 */
package com.appdirect.jbilling.job.pricecalculator.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculator;
import com.appdirect.jbilling.product.ProductPriceDTO;

/**
 * Calculates the Ideal Price of product</br>
 * </br>
 * <b>Assumption : </b> The priceList in {@link ProductPriceDTO} is in sorted
 * order
 * 
 * @author rajesh
 *
 */
class SortedIdealPriceCalculator implements PriceCalculator {

	/**
	 * Calculates Idea price of product</br>
	 * </br>
	 * <b>Logic : </b> Remove 2 Highest and 2 Lowest prices from the list and
	 * get the average of the rest in case list has more than 5 entries, else
	 * just take average of all the elements.
	 * 
	 * @param priceDTO
	 *            with Sorted Price List
	 */
	@Override
	public ProductPriceDTO calculatePrice(ProductPriceDTO priceDTO) {

		List<BigDecimal> prices = priceDTO.getPrices();
		List<BigDecimal> a = new ArrayList<>(prices);
		BigDecimal sum = BigDecimal.ZERO;
		BigDecimal size = BigDecimal.ONE;
		if (a.size() >= 5) {

			// TODO : Need to check, what to do if top two and bottom prices are
			// duplicated
			List<BigDecimal> subList = a.subList(2, a.size() - 2);
			for (BigDecimal price : subList) {
				sum = sum.add(price);
			}
			size = BigDecimal.valueOf(subList.size());
		} else {
			for (BigDecimal price : prices) {
				sum = sum.add(price);
			}
			priceDTO.setDescription("Not Enough Data to calculate Ideal Price Accurately");
			size = BigDecimal.valueOf(prices.size());
		}

		BigDecimal ideaPrice = sum.divide(size, 4, RoundingMode.HALF_DOWN);
		priceDTO.setIdealPrice(ideaPrice);
		return priceDTO;
	}

}
