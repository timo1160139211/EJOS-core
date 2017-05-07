package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.model.Category;
import cn.edu.sdut.softlab.model.Item;
import cn.edu.sdut.softlab.model.Stuff;
import cn.edu.sdut.softlab.service.CategoryFacade;
import cn.edu.sdut.softlab.service.ItemAccountFacade;
import cn.edu.sdut.softlab.service.ItemFacade;
import cn.edu.sdut.softlab.service.StuffFacade;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * 物品管理器.
 * 
 * @author gaoyisheng
 */
@Named("itemManager")
@RequestScoped
public class ItemManagerImpl implements ItemManager {

	@Inject
	private transient Logger logger;
	@Inject
	ItemFacade itemService;

	@Inject
	ItemAccountFacade iaService;
	
	@Inject
	StuffFacade 	userService;

	
	@Inject
	CategoryFacade categoryService;

	@Inject
	private UserTransaction utx;

	@Inject
	EntityManager em;

	@Inject
	Credentials credentials;

	@RequestScoped
	private List<Category> categories;

	public List<Category> getCategories() {
		return categoryService.findAllCategory();
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	private Category selectedCategory;

	private int temporNum;

	public int getTemporNum() {
		return temporNum;
	}

	public void setTemporNum(int temporNum) {
		this.temporNum = temporNum;
	}

	private Item newItem = new Item();
	// 不能在此处 直接new()是因为，
	// boughtTime 这个属性 不是在 页面初始化 的时候，初始化。而是
	// 应该放在Add 这个button 的单击事件里。

	// 上面分析有误， 。。。。。so ?

	// Item 的 set/get 方法

	public Item getNewItem() {
		return newItem;
	}

	public void setNewItem(Item newItem) {
		this.newItem = newItem;
	}

	@Override
	@Produces
	@Named
	@RequestScoped
	public List<Item> getItems() throws Exception {
		try {
			utx.begin();
			return itemService.findAll();
		} finally {
			utx.commit();
		}
	}

	// addItem Button 单击事件
	@Override
	public String addItem() throws Exception {
		try {
			utx.begin();
			/* newItem.setDateBought(new Date()); */
			itemService.create(newItem);
			logger.log(Level.INFO, "Added {0}", newItem);
			return "/AdministratorHome_additems.xhtml?faces-redirect=true";
		} finally {
			utx.commit();
		}
	}

	@Override
	public String deleteItem() throws Exception {

		//////////////////////////////////////////////////// userName 也一样用。
		Item temporItem = itemService.findByName(credentials.getUsername());
		if (temporItem != null) {
			/* currentstuff = temporstuff; */

			utx.begin();
			itemService.remove(temporItem);

			utx.commit();
			logger.log(Level.INFO, "Added {0}");

			return "/AdministratorHome_deleteitem.xhtml?faces-redirect=true";

		} else {

			return "error.xhtml?faces-redirect=true";

		}
	}

	/**
	 * 1.从前台获取 itemname ,and( num , timestamp , borrower, 存记录用) 2.到数据库中查询
	 * 3.修改其中totol字段（入库+=n 出库-=n， 后期交与不同的sevice负责，松耦合提取出来 ）
	 * 4.保存记录到itme_account表，（创建一个新的字段，为以后查出入库明细 用）
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestScoped
	public String itemOut() throws Exception {
		utx.begin();

		Item currentItem = itemService.findByName(this.getNewItem().getName());
		em.clear();

		// 检查 空， 后期提出到 check
		if (currentItem != null) {

			/*
			 * // 暂时 设置为 1, temporNum应为 绑定的参数。 int temporNum = 1;
			 */
			if ((currentItem.getNumTotal() - temporNum) >= 0) {

				currentItem.setNumTotal(currentItem.getNumTotal() - temporNum);

				// 倘若都借出去了，将 状态 status 置为不可借: false
				if ((currentItem.getNumTotal() - temporNum) == 0) {
					currentItem.setStatus("false");
				}
				
				//
				ItemAccountManagerImpl iam= new ItemAccountManagerImpl();
				iam.getNewIA().setItem(currentItem);
				iam.getNewIA().setFlag("Item Out");
				iam.getNewIA().setTimeCheck(new Date());;
				iam.getNewIA().setStuff(userService.findByName("123"));
				iaService.create(iam.getNewIA());			
				
				em.merge(currentItem);
				utx.commit();

				return "/AdministratorHome_itemout.xhtml?faces-redirect=true";

			} else {

				return "/error.xhtml?faces-redirect=true";
				// 不能借到负数吧。
			}
		}

		em.close();
		return "error.xthml?faces-redirect=true";
	}

	/**
	 * 1.从前台获取 itemname ,and( num , timestamp , borrower, 存记录用) 2.到数据库中查询
	 * 3.修改其中totol字段（入库+=n 出库-=n， 后期交与不同的sevice负责，松耦合提取出来 ）
	 * 4.保存记录到itme_account表，（创建一个新的字段，为以后查出入库明细 用）
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestScoped
	public String itemIn() throws Exception {
		utx.begin();

		Item currentItem = itemService.findByName(this.getNewItem().getName());
		em.clear();

		// 检查 空， 后期提出到 check
		if (currentItem != null) {

			if (temporNum > 0) {
				currentItem.setNumTotal(currentItem.getNumTotal() + temporNum);

				// 如果一前都借空了，现在还上了，status 置为 ture.
				if (currentItem.getStatus().equals("false")) {
					currentItem.setStatus("true");
				}

				ItemAccountManagerImpl iam= new ItemAccountManagerImpl();
				iam.getNewIA().setItem(currentItem);
				//iam.getNewIA().setItem(itemService.findByName("123"));
				iam.getNewIA().setFlag("Item In");
				//ERROR
				//cn.edu.sdut.softlab.controller.ItemManagerImpl.itemIn(ItemManagerImpl.java:248)
				//cn.edu.sdut.softlab.controller.ItemManagerImpl$Proxy$_$$_WeldSubclass.itemIn$$super(Unknown Source)
				iam.getNewIA().setTimeCheck(new Date());;
				
				//Stuff u = userService.findByName("123");
				iam.getNewIA().setStuff(userService.findByName("123"));
				iaService.create(iam.getNewIA());			
				//em.persist(iam);
				
				
				em.merge(currentItem);

				utx.commit();

				return "/AdministratorHome_itemin.xhtml?faces-redirect=true";


			}

			em.close();
			return "error.xthml?faces-redirect=true";

		} else {

			return "error.xthml?faces-redirect=true";
		}
	}

	/**
	 * @return the selectedCategory
	 */
	public Category getSelectedCategory() {
		return selectedCategory;
	}

	/**
	 * @param selectedCategory
	 *            the selectedCategory to set
	 */
	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
}
