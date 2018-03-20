/**
 * 
 */
package com.appdirect.jbilling.store.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.store.Store;
import com.appdirect.jbilling.store.base.StoreService;

/**
 * @author rajesh
 *
 */
@Service
public class StoreDBService implements StoreService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StoreDBService.class);
	
	@Autowired
	private StoreRepository repository;


	@Override
	public Store findById(Long storeId) throws NotFoundException {
		Store store = repository.findOne(storeId);
		if(null == store){
			throw new NotFoundException("Store not found in DB with Id : "+storeId);
		}
		return store;
	}


	@Override
	public Store save(Store store) {
		if(null == store){
			LOGGER.warn("Can't save null store");
			return store;
		}
		return repository.save(store);
	}

}
