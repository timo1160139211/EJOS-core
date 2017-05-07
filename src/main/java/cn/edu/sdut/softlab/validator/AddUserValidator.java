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

import cn.edu.sdut.softlab.service.StuffFacade;

/**
 * @author gaoyisheng
 *
 */
@FacesValidator("AddUserValidator")
public class AddUserValidator implements Validator {

	@Inject
	StuffFacade userservice;

	/**
	 * @param value
	 *            前台传入的名字0.0
	 *            
	 *            用户名不为空的情况下，向数据库查找同名User/Item
	 *            如果找到了，说明重复。不能添加
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		// 如果用户名为空，则直接 向前台返回错误
		if (value.equals("")) {
			FacesMessage message = new FacesMessage("用户名不能为空！");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		} 

		/*NULLpointer
		 * 
		 * else {
			// 用户名不为空的情况下，向数据库查找同名USER
			// 如果找到了，说明重复。不能添加
			 
				// Stuff u = userservice.findByName(value.toString());
			Stuff u = userservice.findByName((String) value);
			if (u.getUsername() == (String) value || u.getId() != null || u != null) {
				FacesMessage message = new FacesMessage("该用户名已存在！");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}*/
		
		
		//法2
/*		else {
			if (userservice.findByName((String) value) != null) {
				FacesMessage message = new FacesMessage("该用户名已存在！");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}*/
		
		//法3
		  else {
				if (userservice.findByName((String) value).getClass() != null) {
					FacesMessage message = new FacesMessage("该用户名已存在！");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);
				}		

			
			
			
		}
	}
}
