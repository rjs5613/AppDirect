/**
 * 
 */
package com.appdirect.jbilling.product.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.pricing.base.PricingService;
import com.appdirect.jbilling.product.Product;
import com.appdirect.jbilling.product.ProductPriceDTO;
import com.appdirect.jbilling.product.ProductPriceInfo;
import com.appdirect.jbilling.product.base.ProductService;

/**
 * @author rajesh
 *
 */
@Service
class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class); 

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductPriceInfoRepository priceInfoRepository;
	
	@Autowired
	private PricingService pricingService;
	
	@Override
	public Product findById(Long productId) throws NotFoundException {
		if(null == productId){
			throw new IllegalArgumentException("Can't find product with null product Id");
		}
		Product product = productRepository.findOne(productId);
		if(null==product){
			throw new NotFoundException("Product not present in DB with Id : "+productId);
		}
		return product;
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public ProductPriceDTO getProductPriceInfo(Long productId) throws NotFoundException{
		if(null == productId){
			throw new IllegalArgumentException("Can't find price info with null product Id");
		}
		ProductPriceInfo idealPrice = priceInfoRepository.findByProductId(productId);
		if(null == idealPrice){
			throw new NotFoundException("No Price Information present for product id "+productId+ " please run the price calculator job first" );
		}
		return ProductPriceDTO.fromPriceInfo(idealPrice);
	}

	@Override
	public Product save(Product product) {
		if(null == product){
			throw new IllegalArgumentException("Null product can't be saved");
		}
		return productRepository.save(product);
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public List<ProductPriceDTO> getAggregatedPriceInfo(int pageNumber, int pageSize) {
		
		int startIndex = pageNumber*pageSize;
		List<Object[]> aggregates = priceInfoRepository.getAggregatePrices(startIndex,pageSize);
		List<ProductPriceDTO> dtos = new ArrayList<>();
		if(aggregates.isEmpty()){
			return dtos;
		}

		List<Long> productIds = new ArrayList<>();
		for(Object[] aggregate : aggregates){
			ProductPriceDTO p = generateDTOFromQueryResult(aggregate);
			dtos.add(p);
			productIds.add(p.getProductId());
		}
		
		Map<Long, List<BigDecimal>> productPriceMap = pricingService.getProductPriceMap(productIds);
		
		for(ProductPriceDTO p : dtos){
			p.setPrices(productPriceMap.get(p.getProductId()));
		}
		return dtos;
	}

	private ProductPriceDTO generateDTOFromQueryResult(Object[] aggregate) {
		ProductPriceDTO p = new ProductPriceDTO();
		Object obj = aggregate[0];
		if(null != obj){
			BigInteger a = (BigInteger)obj;
			p.setProductId(a.longValue());
		}
		obj = aggregate[1];
		if(null != obj){
			p.setProductName((String)obj);
		}
		obj = aggregate[2];
		if(null != obj){
			p.setLowestPrice(castToBigDecimal(obj));
		}
		obj = aggregate[3];
		if(null != obj){
			p.setHighestPrice(castToBigDecimal(obj));
		}
		obj = aggregate[4];
		if(null != obj){
			p.setAveragePrice(castToBigDecimal(obj));
		}
		
		obj = aggregate[5];
		if(null != obj){
			BigInteger a = (BigInteger)obj;
			p.setCount(a.intValue());
		}
		obj = aggregate[6];
		if(null != obj){
			p.setBasePrice(castToBigDecimal(obj));
		}
		return p;
	}

	private BigDecimal castToBigDecimal(Object obj) {
		return (BigDecimal)obj;
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public Collection<ProductPriceDTO> savePriceInfos(Collection<ProductPriceDTO> priceDTOs) {
		if(CollectionUtils.isEmpty(priceDTOs)){
			LOGGER.warn("No Pricing Info to save");
			return priceDTOs;
		}
		Collection<ProductPriceInfo> priceInfos = new HashSet<ProductPriceInfo>();
		List<Long> productIds = new ArrayList<>();
		for(ProductPriceDTO priceDTO : priceDTOs){
			productIds.add(priceDTO.getProductId());
			ProductPriceInfo p = ProductPriceDTO.toPriceInfo(priceDTO);
			priceInfos.add(p);
		}
		deletePricingInfoOfProduct(productIds);
		priceInfoRepository.save(priceInfos);
		return priceDTOs;
	}
	
	@Override
	@Transactional(value=TxType.REQUIRED)
	public void deletePricingInfoOfProduct(Collection<Long> productIds){
		if(CollectionUtils.isEmpty(productIds)){
			LOGGER.warn("No ProductIds to delete pricing info");
			return;
		}
		priceInfoRepository.deletePriceInfo(productIds);
	}

}
