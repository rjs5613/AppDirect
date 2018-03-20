/**
 * 
 */
package com.appdirect.JBilling.store;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.appdirect.jbilling.JBillingApplication;
import com.appdirect.jbilling.exceptions.NotFoundException;
import com.appdirect.jbilling.store.Store;
import com.appdirect.jbilling.store.base.StoreService;

/**
 * @author rajesh
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JBillingApplication.class })
public class StoreServiceTest {
	
	@Autowired
	private StoreService storeService;
	
	@Test(expected=NotFoundException.class)
	public void testFindByIdForException() throws NotFoundException{
		storeService.findById(123456L);
	}
	
	@Test
	public void testFindById() throws NotFoundException{
		Store s = new Store();
		s.setName("Test");
		Store storeSaved = storeService.save(s);
		Store storeRetrieved = storeService.findById(storeSaved.getId());
		Assert.assertTrue("Store was not saved", null != storeRetrieved);
		Assert.assertTrue("Saved and Retrived Store are not same", storeSaved.getId() == storeRetrieved.getId());
	}
	
	@Test
	public void testSave(){
		Store storeTOSave = new Store();
		storeTOSave.setName("Test");
		Store storeSaved = storeService.save(storeTOSave);
		Assert.assertTrue("",null != storeSaved.getId());
		Assert.assertTrue("",null != storeSaved.getCreated());
		
	}

}
