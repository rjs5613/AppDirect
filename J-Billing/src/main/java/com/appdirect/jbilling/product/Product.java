/**
 * 
 */
package com.appdirect.jbilling.product;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import com.appdirect.jbilling.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author rajesh
 *
 */
@Entity(name="PRODUCTS")
public class Product extends BaseEntity{
	
	@NotBlank(message="Product name is mandatory")
	private String name;
	
	private String description;
	
	@Column(name="base_price")
	private BigDecimal basePrice;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
