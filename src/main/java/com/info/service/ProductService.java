package com.info.service;

import java.util.List;

import com.info.model.Product;

public interface ProductService {

	public void addProduct(Product product);

	public List<Product> listProduct();

}
