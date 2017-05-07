/*
 * @author gaoyisheng 
 * @date 2017-1-11
 */

package cn.edu.sdut.softlab.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.model.Category;
import cn.edu.sdut.softlab.service.CategoryFacade;


/**
 *   类管理器.
 *  @author gaoyisheng
 */
@Named("categoryManager")
@RequestScoped
public class CategoryManagerImpl implements CategoryManager {


	@Inject
	private EntityManager em;
	
	@Inject
	private transient Logger logger;
	@Inject
	CategoryFacade categoryService;

	@Inject
	private UserTransaction utx;

/*	private List<Category> categories;*/
	
	private Category newCategory = new Category();

	public Category getNewCategory() {
		return newCategory;
	}

	public void setNewStuff(Category newCategory) {
		this.newCategory = newCategory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Produces
	@Named
	@RequestScoped
	public List<Category> getCategories() throws Exception {
		try {
			utx.begin();
			
			//
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Category.class));
			
			return em.createQuery(cq).getResultList();
		} finally {
			utx.commit();
		}
	}

	
	@Override
	public String addCategory() throws Exception {
		try {
			utx.begin();
			categoryService.create(newCategory);
			logger.log(Level.INFO, "Added {0}", newCategory);
			return "/Categories.xhtml?faces-redirect=true";
		} finally {
			utx.commit();
		}
	}
	

	

}
