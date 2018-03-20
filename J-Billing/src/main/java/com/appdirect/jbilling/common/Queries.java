package com.appdirect.jbilling.common;

/**
 * 
 * @author rajesh
 *
 */
public class Queries {
	
	private Queries(){
		
	}

	public static final String PRICE_AGGREGATE_PAGINATED = "select "
			+ "ps.product_id, ps.product_name,min(ps.product_price), max(ps.product_price), "
			+ "avg(ps.product_price), count(ps.*) , p.base_price "
			+ "from product_store_price ps, products p "
			+ "where p.id = ps.product_id "
			+ "group by ps.product_id, ps.product_name, p.base_price "
			+ "order by ps.product_id LIMIT :pageSize OFFSET :startIndex";
	
	public static final String DELETE_PRODUCT_PRICE_INFO="delete from product_price_info "
			+ "where product_id in :productIds";

	public static final String PRODUCT_PRICE_PAGINATED = "select product_id, product_price "
			+ "from product_store_price where product_id in :productIds "
			+ "order by product_id, product_price";

}
