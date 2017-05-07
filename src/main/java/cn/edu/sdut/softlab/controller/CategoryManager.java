/*
 * @author gaoyisheng
 */


package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.model.Category;
import java.util.List;

public interface CategoryManager {

	/**
	 * 获得所有 Category 列表.
	 *
	 * @return 所有 Category 列表
	 * @throws Exception
	 */
	List<Category> getCategories() throws Exception;

	/**
	 * 新增 Category .
	 *
	 * @return 返回 Category 列表页面
	 * @throws Exception
	 */
	String addCategory() throws Exception;

}
