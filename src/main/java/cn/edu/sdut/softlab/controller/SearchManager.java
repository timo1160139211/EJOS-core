///**
// * 
// */
//package cn.edu.sdut.softlab.controller;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.persistence.EntityManager;
//
//import org.primefaces.component.datatable.DataTable;
//
//import cn.edu.sdut.softlab.entity.Item;
//import cn.edu.sdut.softlab.service.SearchFacade;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.enterprise.context.Dependent;//
//import javax.faces.bean.ApplicationScoped;
//
///**
// * @author gaoyisheng
// *
// */
//@Named("SearchManager")
//@Dependent
//@ApplicationScoped	//SessionScoped 得到的List<>无法sortBy  改为Faces.Bean.ApplScoped..(模仿primefaces)还是不行 = =               
//public class SearchManager implements Serializable{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Inject
//	EntityManager em;
//	
//	//bukeyi = =
///*	private Item newItem = new Item();
//		
//	public Item getNewItem() {
//		return newItem;
//	}
//
//	public void setNewItem(Item newItem) {
//		this.newItem = newItem;
//	}
//	*/
//
//	//作为中间变量的存在。类似Credentials
//	@Inject
//	Tempor tem;
//	
//	public Tempor getTem() {
//		return tem;
//	}
//
//	public void setTem(Tempor tem) {
//		this.tem = tem;
//	}
//
//
//	@Inject
//	SearchFacade searchService;
//	
//	
//	public List<Item> getItemsSearchDT(){
//		String i = tem.getName();
//		
//		if (i == null || i.equals("")){
//			return searchService.allDT();
//		}
//		return searchService.fuzzySearch(i);
//	}
//	
//	//1 模仿 primefaces 解决sortBy
//	//2 用DataTable解决?无法sortBy    
//	private DataTable dt= new DataTable();
//	/* <summary>
// DataTable中使用Order By排序与Where过滤
// </summary>
//private void Bind()
//{
//    //这里构造一个数据源
//    DataTable dt = new DataTable();
//    dt.Columns.Add("ID", typeof(System.String));
//    dt.Columns.Add("uName", typeof(System.String));
//    dt.Columns.Add("uDate", typeof(System.DateTime));
//    for (int i = 0; i < 10; i++)
//    {
//        DataRow dr = dt.NewRow();
//        dr["ID"] = i.ToString();
//        dr["uName"] = "name" + i;
//        dt.Rows.Add(dr);
//    }
//    dt.DefaultView.Sort = "ID asc";//相当于Order By
//    dt.DefaultView.RowFilter = "ID>5";//相当于Where
//    GridView1.DataSource = dt;
//    GridView1.DataBind();
//}
//
//复制代码
//
//后台调试了一下发现dt的排序没变，这时需要加一句dt = dt.DefaultView.ToTable();
//
//*/
//	
//	
//	
//	
//	
//	/*
//	public List<Item> categorySearch(String name) {
//    	
//    	return em.createQuery("SELECT i FROM Item i " + 
//    								"WHERE i.name LIKE '%?1%' ")
//    			.setParameter(1, name)
//    			.getResultList();
//       
//    }*/
//
//	//按类名查询
//	public List<Item> getSearchByCategoryNameDT(){
//		String i = tem.getCategoryName();
//		
//		if (i == null || i.equals("")){
//			return searchService.allDT();
//		}
//		return searchService.searchByCategoryName(i);
//	}
//	
//	
//}
