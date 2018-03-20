package com.appdirect.jbilling.pricing.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appdirect.jbilling.common.Queries;
import com.appdirect.jbilling.pricing.ProductStorePrice;

interface ProductStorePriceRepository extends JpaRepository<ProductStorePrice, Long> {

	@Query(value=Queries.PRODUCT_PRICE_PAGINATED, nativeQuery=true)
	List<Object[]> getProductPrices(@Param("productIds") List<Long> productIds);

}
