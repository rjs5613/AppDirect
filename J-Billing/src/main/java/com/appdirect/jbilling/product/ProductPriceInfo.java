/**
 * 
 */
package com.appdirect.jbilling.product;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.appdirect.jbilling.common.BaseEntity;

/**
 * @author rajesh
 *
 */
@Entity(name = "product_price_info")
public class ProductPriceInfo  extends BaseEntity{

	private Long productId;

	private String productName;

	private String description;

	private BigDecimal basePrice;

	private BigDecimal averagePrice;

	private BigDecimal lowestPrice;

	private BigDecimal highestPrice;

	private BigDecimal idealPrice;

	private int count;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	public BigDecimal getIdealPrice() {
		return idealPrice;
	}

	public void setIdealPrice(BigDecimal idealPrice) {
		this.idealPrice = idealPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
