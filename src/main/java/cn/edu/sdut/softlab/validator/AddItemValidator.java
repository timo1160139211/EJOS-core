/**
 * 
 */
package cn.edu.sdut.softlab.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import cn.edu.sdut.softlab.service.ItemFacade;

/**
 * @author gaoyisheng
 *
 */
@FacesValidator("AddItemValidator")
public class AddItemValidator implements Validator {

	@Inject
	ItemFacade itemservice;

	/**
	 * 
	 * @param value
	 *            前台传入的名字0.0
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		// 如果用户名为空，则直接 向前台返回错误
		if (value.equals("")) {
			FacesMessage message = new FacesMessage("物品名不能为空！");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		
		/*方案一
		 * 报 null pointer  ERROR
		 * 
		 * else {
			// 用户名不为空的情况下，向数据库查找同名USER
			// 如果找到了，说明重复。不能添加
			Item i = itemservice.findByName((String) value);
			
			if (i.getName() == (String) value || i.getId() != null || i != null) {
				FacesMessage message = new FacesMessage("该物品已存在！");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}*/
		
		
		//方案二：
		else {
			//Item i = itemservice.findByName((String) value);
			if (itemservice.findByName((String) value) != null) {
				FacesMessage message = new FacesMessage("该物品已存在！");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		
		//法3
		/*
		else{
			if (itemservice.findByName((String) value).getClass()!= null) {
				FacesMessage message = new FacesMessage("该物品已存在！");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}else{
				return ;
				}
			
			
			
		*/	
			
			
		}
		
	}
}
