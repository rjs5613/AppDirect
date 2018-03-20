/**
 * 
 */
package com.appdirect.jbilling.pricing.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.pricing.ProductStorePrice;
import com.appdirect.jbilling.pricing.StoreProductPricingDTO;
import com.appdirect.jbilling.pricing.base.PricingService;
import com.appdirect.jbilling.product.Product;
import com.appdirect.jbilling.product.base.ProductService;
import com.appdirect.jbilling.store.Store;
import com.appdirect.jbilling.store.base.StoreService;

/**
 * @author rajesh
 *
 */
@Service
class PricingDBService implements PricingService{
	
	@Autowired
	private ProductStorePriceRepository productStorePriceRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StoreService storeService;

	@Override
	public StoreProductPricingDTO createProductPrice(StoreProductPricingDTO price) throws NotFoundException {
		Product p = productService.findById(price.getProduct());
		Store s = storeService.findById(price.getStore());
		ProductStorePrice prodStorePrice = new ProductStorePrice();
		prodStorePrice.setProduct(p);
		prodStorePrice.setStore(s);
		prodStorePrice.setProductPrice(price.getPrice());
		prodStorePrice.setProductName(p.getName());
		prodStorePrice.setStoreName(s.getName());
		ProductStorePrice productStorePrice = productStorePriceRepository.save(prodStorePrice);
		return StoreProductPricingDTO.fromProdStorePrice(productStorePrice);
	}

	@Override
	public Map<Long, List<BigDecimal>> getProductPriceMap(List<Long> productIds) {
		
		List<Object[]> productPrices = productStorePriceRepository.getProductPrices(productIds);
		Map<Long, List<BigDecimal>> productPriceMap = new HashMap<>();
		for(Object[] objArray : productPrices){
			List<BigDecimal> priceList = new ArrayList<>();
			Long productId = ((BigInteger)objArray[0]).longValue();
			BigDecimal price = (BigDecimal)objArray[1];
			if(productPriceMap.containsKey(productId)){
				priceList = productPriceMap.get(productId);
				priceList.add(price);
			}else{
				priceList.add(price);
			}
			productPriceMap.put(productId, priceList);
		}
		return productPriceMap;
	}

}
