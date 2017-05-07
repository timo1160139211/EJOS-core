/**
 * 
 */
package cn.edu.sdut.softlab.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import cn.edu.sdut.softlab.model.Category;
import cn.edu.sdut.softlab.service.CategoryFacade;

/**
 * @author gaoyisheng
 *
 */
@ManagedBean(name = "myCategoryConverter")
@FacesConverter(forClass = Category.class, value = "myCategoryConverter")
public class CategoryConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CategoryFacade categoryservice;

	@Inject
	FacesContext fc;

	/**
	 * @param name
	 *            (String)
	 * @return Object
	 * 
	 *         传入一个 category_name ，根据name查找返回一个 category 对象，
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String name) throws ConverterException{

		if (!name.equals("") && name != null) {

			//根据名字查找到对象      不行 
/*			Category category = categoryservice.findSpecifiedCategoryByName(name);
			
			return category.getName();*/
			
			//法二,直接
			return categoryservice.findSpecifiedCategoryByName(name).getId();
			
			
		}

/*		if (name != null) {

			Category category = categoryservice.findByName(name);

			return category.getName();
		}*/

		return null;
	}

	/**
	 * @param obj
	 * @return String
	 * 
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		/* return String.valueOf(((Category) obj)); 不行 = = why */

		if (obj instanceof Category)
			
			return ((Category) obj).getName();
			//return String.valueOf(((Category) obj));// 调用的 Category.toString()
													// ??!!
		else {
			return null;
		}
	}

}