package com.info.service;

import java.util.List;

import com.info.model.Category;

public interface CategoryService {
	
	public void addCategory(Category category);
	
	public List<Category> listCategory();
	
}
