
/*
 * @author gaoyisheng
 */

package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.model.Item;
import java.util.List;

public interface ItemManager {

	/**
	 * 获得所有 Item 列表.
	 *
	 * @return 所有 Item 列表
	 * @throws Exception
	 */
	List<Item> getItems() throws Exception;

	/**
	 * 新增 Item .
	 *
	 * @return 返回 Item 列表页面
	 * @throws Exception
	 */
	String addItem() throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	String deleteItem() throws Exception;

}
