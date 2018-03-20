/**
 * 
 */
package com.appdirect.jbilling.product;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author rajesh
 *
 */
public class ProductPriceDTO {

	@JsonIgnore
	private List<BigDecimal> prices;

	@JsonIgnore
	private Long productId;

	private String productName;

	private String description;

	private BigDecimal basePrice;

	private BigDecimal averagePrice;

	private BigDecimal lowestPrice;

	private BigDecimal highestPrice;

	private BigDecimal idealPrice;

	private int count;

	public List<BigDecimal> getPrices() {
		return prices;
	}

	public void setPrices(List<BigDecimal> prices) {
		this.prices = prices;
	}

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

	public static ProductPriceDTO fromPriceInfo(ProductPriceInfo priceInfo) {
		ProductPriceDTO priceDTO = new ProductPriceDTO();
		priceDTO.setAveragePrice(priceInfo.getAveragePrice());
		priceDTO.setBasePrice(priceInfo.getBasePrice());
		priceDTO.setProductName(priceInfo.getProductName());
		priceDTO.setCount(priceInfo.getCount());
		priceDTO.setDescription(priceInfo.getDescription());
		priceDTO.setHighestPrice(priceInfo.getHighestPrice());
		priceDTO.setLowestPrice(priceInfo.getLowestPrice());
		priceDTO.setIdealPrice(priceInfo.getIdealPrice());
		return priceDTO;
	}

	public static ProductPriceInfo toPriceInfo(ProductPriceDTO priceDTO) {
		ProductPriceInfo priceInfo = new ProductPriceInfo();
		priceInfo.setProductId(priceDTO.getProductId());
		priceInfo.setProductName(priceDTO.getProductName());
		priceInfo.setLowestPrice(priceDTO.getLowestPrice());
		priceInfo.setHighestPrice(priceDTO.getHighestPrice());
		priceInfo.setIdealPrice(priceDTO.getIdealPrice());
		priceInfo.setAveragePrice(priceDTO.getAveragePrice());
		priceInfo.setBasePrice(priceDTO.getBasePrice());
		priceInfo.setCount(priceDTO.getCount());
		priceInfo.setDescription(priceDTO.getDescription());
		return priceInfo;
	}

}
