package com.appdirect.jbilling.common;

import java.time.Instant;
import java.util.Date;

import javax.persistence.PrePersist;

public class CreateListener  {
	
	@PrePersist
	public void beforePersist(BaseEntity baseEntity){
		
		baseEntity.setCreated(Date.from(Instant.now()));
		
	}
	
	

}
