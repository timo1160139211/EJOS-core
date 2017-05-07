/**
 * 
 */
package cn.edu.sdut.softlab.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.model.ItemAccount;
import cn.edu.sdut.softlab.service.ItemAccountFacade;
import cn.edu.sdut.softlab.service.ItemFacade;
import cn.edu.sdut.softlab.service.StuffFacade;

/**
 * @author gaoyisheng
 *
 */
@Named("itemAccountManager")
@RequestScoped
public class ItemAccountManagerImpl {
	
	@Inject
	ItemAccountFacade iaService;
	
	@Inject
	ItemFacade itemService;
	
	@Inject
	StuffFacade userService;
	
	
	@Inject
	private UserTransaction utx;

	@Inject
	EntityManager em;
	
	private ItemAccount newIA = new ItemAccount();
	
	public ItemAccountManagerImpl(){
		
	}
	
	//构造时传入数据 最笨办法 实现。
	public ItemAccountManagerImpl(String flag,String itemName,String userName){
		this.newIA.setFlag(flag);
		this.newIA.setItem(itemService.findByName(itemName));
		this.newIA.setStuff(userService.findByName(userName));
		
		//this.newIA.setStuff(userService.findByName("1"));
	}
	
	/*
	//调用本方法 返回一个 ItemAccount 用来存入数据库
	public ItemAccount createIA(){
		return this.newIA;
	}
	*/
	public List<ItemAccount> getItemAccounts() throws Exception {
		try {
			utx.begin();
			return iaService.findAll();
		} finally {
			utx.commit();
		}
	}

	/**
	 * @return the newIA
	 */
	public ItemAccount getNewIA() {
		return newIA;
	}

	/**
	 * @param newIA the newIA to set
	 */
	public void setNewIA(ItemAccount newIA) {
		this.newIA = newIA;
	}
	
	
}
