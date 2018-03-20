/**
 * 
 */
package com.appdirect.jbilling.product.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appdirect.jbilling.common.Queries;
import com.appdirect.jbilling.product.ProductPriceInfo;

/**
 * @author rajesh
 *
 */
interface ProductPriceInfoRepository extends JpaRepository<ProductPriceInfo, Long> {

	ProductPriceInfo findByProductId(Long productId);

	@Query(value = Queries.PRICE_AGGREGATE_PAGINATED, nativeQuery = true)
	List<Object[]> getAggregatePrices(@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	@Query(value = Queries.DELETE_PRODUCT_PRICE_INFO, nativeQuery = true)
	@Modifying
	void deletePriceInfo(@Param("productIds") Collection<Long> productIds);

}
