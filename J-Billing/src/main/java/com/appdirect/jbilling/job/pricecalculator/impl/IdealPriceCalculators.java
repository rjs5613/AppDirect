package com.appdirect.jbilling.job.pricecalculator.impl;

import com.appdirect.jbilling.job.pricecalculator.base.PriceCalculator;

/**
 * Factory for <b>Ideal</b> {@link PriceCalculator}
 * 
 * @author rajesh
 *
 */
public class IdealPriceCalculators {

	/**
	 * Gets the <b>Ideal</b> {@link PriceCalculator} based on the strategy
	 * 
	 * @param strategy
	 * @return
	 */
	public static PriceCalculator getInstance(String strategy) {

		return new SortedIdealPriceCalculator();

	}

}
