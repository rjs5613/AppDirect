package com.appdirect.jbilling.pricing.base;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.pricing.StoreProductPricingDTO;

public interface PricingService {
	
	StoreProductPricingDTO createProductPrice(StoreProductPricingDTO price) throws NotFoundException;
	
	Map<Long, List<BigDecimal>> getProductPriceMap(List<Long> productIds);
	
	

}
