///*
// * Copyright 2016 Su Baochen and individual contributors by the 
// * @authors tag. See the copyright.txt in the distribution for
// * a full listing of individual contributors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package cn.edu.sdut.softlab.service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.ejb.Stateless;
//import javax.inject.Named;
//
//import cn.edu.sdut.softlab.entity.Category;
//
//@Stateless
//@Named("category")
//public class CategoryFacade extends AbstractFacade<Category> {
//
//	public CategoryFacade() {
//		super(Category.class);
//	}
//
//	public Category findByName(String name) {
//		Map<String, Object> parameters = new HashMap<>(0);
//		parameters.put("name", name);
//		return findSingleByNamedQuery("Category.findByName", parameters, Category.class).get();
//	}
//
//	public Category findById(int id) {
//		Map<String, Object> parameters = new HashMap<>(0);
//		parameters.put("id", id);
//		return findSingleByNamedQuery("Category.findById", parameters, Category.class).get();
//	}
//
//	/*
//	 * 查找指定 category,服务于 converter
//	 */
//	public Category findSpecifiedCategoryByName(String name) {
//		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("name", name);
//		return findSingleByNamedQuery("Category.findByName", parameters, Category.class).get();
//	}
//
//	public List<Category> findAllCategory() {
//		return findAll();
//	}
//
//}
