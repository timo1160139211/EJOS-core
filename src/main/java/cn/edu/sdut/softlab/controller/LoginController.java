/*
 * Copyright 2016 Su Baochen and individual contributors by the 
 * @authors tag. See the copyright.txt in the distribution for
 * a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.*;
import cn.edu.sdut.softlab.service.StudentFacade;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named("login")
public class LoginController implements Serializable {

	private static final long serialVersionUID = 7965455427888195913L;

	@Inject
	private Credentials credentials;

//	@SuppressWarnings("cdi-ambiguous-dependency")
//	@Inject       WHY???
	StudentFacade studentService;

	@Inject
	FacesContext facesContext;

	private Student currentUser = null;

	/**
	 * 处理登录逻辑.
	 */
	public void login() {
		Student student = studentService.findByStuNOAndPassword(credentials.getStuNO(), credentials.getPassword());
		if (student != null) {
			currentUser = student;
			facesContext.addMessage(null, new FacesMessage("Welcome, " + currentUser.getName()));
		}
	}

//	/**
//	 * 处理退出登录逻辑.
//	 */
//	public void logout() {
//		facesContext.addMessage(null, new FacesMessage("Goodbye, " + currentUser.getUsername()));
//		currentUser = null;
//	}
//
//	/**
//	 * 判断用户是否登录.
//	 *
//	 * @return true：已经登录；false：没有登录
//	 */
//	public boolean isLoggedIn() {
//		return currentUser != null;
//	}
//
//	@Produces
//	@LoggedIn
//	public Stuff getCurrentUser() {
//		return currentUser;
//	}

}
