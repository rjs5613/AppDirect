package com.appdirect.JBilling.store;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.appdirect.jbilling.JBillingApplication;
import com.appdirect.jbilling.common.CommonUtils;
import com.appdirect.jbilling.pricing.StoreProductPricingDTO;
import com.appdirect.jbilling.store.Store;
import com.appdirect.jbilling.store.StoreController;


@RunWith(SpringRunner.class)
@WebMvcTest(StoreController.class)
@ContextConfiguration(classes={JBillingApplication.class})
public class StoreControllerTest {
	
	   @Autowired
	   private MockMvc mvc;

	   @MockBean
	   private StoreController storeController;

	   @Test
	   public void testStoreCreateBadRequest() throws Exception {
	       Store priceDTO = new Store();
	       priceDTO.setDescription("123");
	       byte[] byteArray = CommonUtils.serializeToByteArray(priceDTO);
	       mvc.perform(MockMvcRequestBuilders.post("/stores").content(byteArray)
	               .contentType(APPLICATION_JSON))
	               .andExpect(MockMvcResultMatchers.status().isBadRequest());
	   }
	   
	   @Test
	   public void testStoreCreate() throws Exception {
	       Store priceDTO = new Store();
	       priceDTO.setDescription("123");
	       priceDTO.setName("123");
	       byte[] byteArray = CommonUtils.serializeToByteArray(priceDTO);
	       mvc.perform(MockMvcRequestBuilders.post("/stores").content(byteArray)
	               .contentType(APPLICATION_JSON))
	               .andExpect(MockMvcResultMatchers.status().isOk());
	   }
	   
	   @Test
	   public void testUpdateProductBadRequest() throws Exception {
		   StoreProductPricingDTO priceDTO = new StoreProductPricingDTO();
	       priceDTO.setStore(1L);
	       priceDTO.setPrice(BigDecimal.TEN);
	       byte[] byteArray = CommonUtils.serializeToByteArray(priceDTO);
	       mvc.perform(MockMvcRequestBuilders.post("/stores/products").content(byteArray)
	               .contentType(APPLICATION_JSON))
	               .andExpect(MockMvcResultMatchers.status().isBadRequest());
	   }
	   
	   @Test
	   public void testUpdateProduct() throws Exception {
		   StoreProductPricingDTO priceDTO = new StoreProductPricingDTO();
	       priceDTO.setProduct(1L);
	       priceDTO.setStore(1L);
	       priceDTO.setPrice(BigDecimal.TEN);
	       byte[] byteArray = CommonUtils.serializeToByteArray(priceDTO);
	       mvc.perform(MockMvcRequestBuilders.post("/stores/products").content(byteArray)
	               .contentType(APPLICATION_JSON))
	               .andExpect(MockMvcResultMatchers.status().isOk());
	   }
	   

}
