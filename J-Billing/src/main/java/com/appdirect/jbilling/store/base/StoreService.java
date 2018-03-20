/**
 * 
 */
package com.appdirect.jbilling.store.base;

import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.store.Store;

/**
 * @author rajesh
 *
 */
public interface StoreService {

	Store findById(Long store) throws NotFoundException;

	Store save(Store store);

}
