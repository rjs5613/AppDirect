package com.appdirect.JBilling.product;

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
import com.appdirect.jbilling.product.Product;
import com.appdirect.jbilling.product.ProductController;
import com.appdirect.jbilling.product.ProductPriceDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = { JBillingApplication.class })
public class ProductControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductController productController;

	@Test
	public void testGetProductPrice() throws Exception {
		ProductPriceDTO priceDTO = new ProductPriceDTO();
		priceDTO.setProductId(1L);
		priceDTO.setProductName("Test123");
		mvc.perform(MockMvcRequestBuilders.get("/products/1/prices").contentType(APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetProductPriceBadRequest() throws Exception {
		ProductPriceDTO priceDTO = new ProductPriceDTO();
		priceDTO.setProductId(1L);
		priceDTO.setProductName("Test123");
		mvc.perform(MockMvcRequestBuilders.get("/products/a/prices").contentType(APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void testSaveProductBadRequest() throws Exception {
		Product p = new Product();
		p.setBasePrice(BigDecimal.TEN);
		byte[] valueAsBytes = CommonUtils.serializeToByteArray(p);
		mvc.perform(MockMvcRequestBuilders.post("/products").content(valueAsBytes).contentType(APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testSaveProduct() throws Exception {
		Product p = new Product();
		p.setBasePrice(BigDecimal.TEN);
		p.setName("Test123");
		byte[] valueAsBytes = CommonUtils.serializeToByteArray(p);
		mvc.perform(MockMvcRequestBuilders.post("/products").content(valueAsBytes).contentType(APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


}
