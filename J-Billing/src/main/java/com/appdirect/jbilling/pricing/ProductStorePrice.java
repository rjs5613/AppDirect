/**
 * 
 */
package com.appdirect.jbilling.pricing;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.appdirect.jbilling.common.BaseEntity;
import com.appdirect.jbilling.product.Product;
import com.appdirect.jbilling.store.Store;

/**
 * @author rajesh
 *
 */
@Entity(name="product_store_price")
public class ProductStorePrice extends BaseEntity{
	
	@OneToOne
	private Product product;

	@Column(name="product_name")
	private String productName;
	
	@OneToOne
	private Store store;
	
	@Column(name="store_name")
	private String storeName;
	
	private BigDecimal productPrice;


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	

}
