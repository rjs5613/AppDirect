/**
 * 
 */
package com.appdirect.jbilling.product.base;

import java.util.Collection;
import java.util.List;

import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.product.Product;
import com.appdirect.jbilling.product.ProductPriceDTO;

/**
 * @author rajesh
 *
 */
public interface ProductService {

	Product findById(Long product) throws NotFoundException;

	/**
	 * Gets Pricing info of product
	 * 
	 * @param productId
	 * @return
	 * @throws NotFoundException
	 *             if price info is not present n DB
	 */
	ProductPriceDTO getProductPriceInfo(Long productId) throws NotFoundException;

	Product save(Product p);

	/**
	 * Gets Paginated List of {@link ProductPriceDTO}
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<ProductPriceDTO> getAggregatedPriceInfo(int pageNumber, int pageSize);

	/**
	 * Saves Price info to DB
	 * 
	 * @param priceDTOs
	 * @return
	 */
	Collection<ProductPriceDTO> savePriceInfos(Collection<ProductPriceDTO> priceDTOs);

	/**
	 * Deletes Pricing info of Products
	 * 
	 * @param productIds
	 */
	void deletePricingInfoOfProduct(Collection<Long> productIds);

}
