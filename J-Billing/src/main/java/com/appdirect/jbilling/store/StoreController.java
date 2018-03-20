/**
 * 
 */
package com.appdirect.jbilling.store;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.jbilling.common.ResponseContainer;
import com.appdirect.jbilling.common.ResponseUtil;
import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.pricing.StoreProductPricingDTO;
import com.appdirect.jbilling.pricing.base.PricingService;
import com.appdirect.jbilling.store.base.StoreService;

/**
 * @author rajesh
 *
 */
@RestController
@RequestMapping("/stores")
public class StoreController {

	@Autowired
	private StoreService storeService;

	@Autowired
	private PricingService pricingService;

	@PostMapping
	public ResponseEntity<ResponseContainer> createStore(@Valid @RequestBody Store store) {
		Store savedStore = storeService.save(store);
		return ResponseUtil.getResponse(savedStore, HttpStatus.CREATED);

	}

	@PostMapping("/products")
	public ResponseEntity<ResponseContainer> updateProduct(@Valid @RequestBody StoreProductPricingDTO pricingDTO)
			throws NotFoundException {
		StoreProductPricingDTO productPrice = pricingService.createProductPrice(pricingDTO);
		return ResponseUtil.getResponse(productPrice, HttpStatus.OK);
	}
}
