/**
 * 
 */
package com.appdirect.jbilling.store.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appdirect.jbilling.store.Store;

/**
 * @author rajesh
 *
 */
interface StoreRepository extends JpaRepository<Store, Long> {

}
