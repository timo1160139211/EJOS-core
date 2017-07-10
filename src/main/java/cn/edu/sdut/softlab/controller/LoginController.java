

package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.*;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;
import cn.edu.sdut.softlab.service.StudentFacade;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named("login")
public class LoginController implements Serializable {

	private static final long serialVersionUID = 7965455427888195913L;

	@Inject
	private Credentials credentials;

	@Inject       
	StudentFacade studentService;

	@Inject
	FacesContext facesContext;

	private Student currentUser = null;
	
	@Produces
	@LoggedIn
	public Student getCurrentUser() {
		return currentUser;
	}

	/**
	 * 处理登录逻辑.
	 */
	public void login() {
		Student student = studentService.findByStuNOAndPassword(credentials.getNO(), credentials.getPassword());
		if (student != null) {
			currentUser = student;
			facesContext.addMessage(null, new FacesMessage("Welcome, " + currentUser.getName()));
		}
	}

	/**
	 * 处理退出登录逻辑.
	 */
	public void logout() {
		facesContext.addMessage(null, new FacesMessage("Goodbye, " + currentUser.getName()));
		currentUser = null;
	}

	/**
	 * 判断用户是否登录.
	 *
	 * @return true：已经登录；false：没有登录
	 */
	public boolean isLoggedIn() {
		return currentUser != null;//才看明白，null != null 没登录！
	}

	public void checkLogin(ComponentSystemEvent event){
		if(!this.isLoggedIn()){
			FacesContext context = FacesContext.getCurrentInstance();
			
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
			handler.performNavigation("login");
		}
		
	}
	
	public void checkLoginExt(ComponentSystemEvent event){
		if(!this.isLoggedIn()){
			FacesContext context = FacesContext.getCurrentInstance();
			
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
			handler.performNavigation("../login");
		}
		
	}

}
