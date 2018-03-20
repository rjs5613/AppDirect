/**
 * 
 */
package com.appdirect.jbilling.product.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appdirect.jbilling.product.Product;

/**
 * @author rajesh
 *
 */
interface ProductRepository extends JpaRepository<Product, Long> {

}
