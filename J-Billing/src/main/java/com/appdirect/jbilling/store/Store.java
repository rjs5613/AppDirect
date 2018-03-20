/**
 * 
 */
package com.appdirect.jbilling.store;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

import com.appdirect.jbilling.common.BaseEntity;

/**
 * @author rajesh
 *
 */
@Entity(name="stores")
public class Store extends BaseEntity{
	
	@NotBlank(message="Store Name is Mandatory")
	private String name;
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
