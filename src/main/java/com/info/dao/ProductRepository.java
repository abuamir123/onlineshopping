package com.info.dao;


import com.info.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	//public List<Product> findByCategory_CategoryId(long CategoryId);
	
}
