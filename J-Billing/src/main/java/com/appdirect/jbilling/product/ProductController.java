/**
 * 
 */
package com.appdirect.jbilling.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.jbilling.common.ResponseContainer;
import com.appdirect.jbilling.common.ResponseUtil;
import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.product.base.ProductService;

/**
 * Controller for products
 * 
 * @author rajesh
 *
 */

@RestController
@RequestMapping(path = "/products")
public class ProductController {


	@Autowired
	private ProductService productService;

	/**
	 * 
	 * @param p
	 * @return
	 */
	@PostMapping
	public ResponseEntity<ResponseContainer> createProduct(@Valid @RequestBody Product p) {
		Product product = productService.save(p);
		return ResponseUtil.getResponse(product, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param productId
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping("/{id}/prices")
	public ResponseEntity<ResponseContainer> getProductPrice(@PathVariable("id") Long productId) throws NotFoundException {
		ProductPriceDTO p = productService.getProductPriceInfo(productId);
		return ResponseUtil.getResponse(p, HttpStatus.OK);
	}

}
