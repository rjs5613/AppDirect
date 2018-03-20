package com.appdirect.jbilling.pricing;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class StoreProductPricingDTO {
	
	@NotNull(message="Store Id can't be empty")
	private Long store;
	
	@NotNull(message="product Id can't be empty")
	private Long product;
	
	@NotNull(message="product price can't be empty")
	private BigDecimal price;
	
	private Date created;

	public Long getStore() {
		return store;
	}

	public void setStore(Long store) {
		this.store = store;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public static StoreProductPricingDTO fromProdStorePrice(ProductStorePrice productStorePrice){
		StoreProductPricingDTO p = new StoreProductPricingDTO();
		p.setStore(productStorePrice.getStore().getId());
		p.setProduct(productStorePrice.getProduct().getId());
		p.setPrice(productStorePrice.getProductPrice());
		return p;
	}

}
