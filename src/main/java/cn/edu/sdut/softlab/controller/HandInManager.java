/*
 * file_name: HandInManager.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年6月6日 
 *       https://www.gaoyisheng.site
 *       https://github.com/timo1160139211
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.sdut.softlab.controller;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.entity.Achievement;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;
import cn.edu.sdut.softlab.service.HandInService;

/**
 * @author GaoYisheng 2017年6月6日 TODO 提交实验报告的管理类
 */
@SessionScoped
@Named("handInManager")
public class HandInManager implements Serializable {

	private static final long serialVersionUID = 7965455427888195913L;

	@Inject
	private transient Logger logger;
	
	@Inject
	private UserTransaction utx;

	@Inject
	EntityManager em;
	
	@Inject
	FacesContext facesContext;
	
	@Inject
	private HandInService his;
	
	@Inject
	private ExpReport exp;
	/*
	 * String fileName;//文件名 
	 * String result;//返回值 
	 * String filePath;//文件保存的路径 
	 * String answerText;//代码(答案)
	 */

	private Achievement achie;
	/*
 		  	int id;
        	String answer;
		  	String answerPath;
		  	String result;
		 	int score;
	 		ItemBank itemBank;
	 		Student student;
	 * */
	


	
	/** *********************************************************************************
	 * core  1/3
	 * 保存编辑内容 到数据库=<<achievement>>
	 *     1.获取题目[PId]，学生学号[SId],获取文件命名[fileName]
	 *     2.获取{editor}的值[value],保存到 表<achie> 的 (answer)字段中
	 *     3.把 "SId/PId/fileName" 作为路径，保存到 表<achie> 的(answerPath)中
	 *     4.
	 *  *********************************************************************************
	 */
	void save() throws Exception {
		//将路径加上参数配置成全路径    /data/ejos/exp/SId/PId/fileName
		String path = "/data/ejos/exp/" + exp.getCurrentUser().getId() 
							+ "/" + exp.getQuestion().getId() + "/" + exp.getFileName() ;
		exp.setFilePath(path);
		merge(exp,achie);//将exp赋值给achie
		
		try {
			utx.begin();
			his.create(achie);
			logger.log(Level.INFO, "Added {0}", achie);
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			utx.commit();
		}
		
	}

	
	/** *********************************************************************************
	 * core  2/3
	 * 保存并编译。保存到数据库=<<achievement>>，保存到文件
	 *     1.获取题目[PId]，学生学号[SId],[StuNO],获取文件命名[fileName]
	 *     2.获取{editor}的值[value],保存到 表<achie> 的(answer)字段中
	 *     3.把 "StuNO/PId/fileName" 作为路径，保存到 表<achie> 的(answerPath)中
	 *     4.开启线程，调用 #javac 、#java 命令,并获取返回值[return]
	 *     5.将返回值[return] 写入表<achie> 的(result)字段中
	 *     6.比较[return]与 表<itemBank>中的(result)字段是否相等 => - 通过   80
	 *                                          .eqaul()   - 未通过 20 
	 *       存到表<achie>的(score)字段中
	 *     7. 
	 *  *********************************************************************************
	 */
	void saveAndCompile() {

	}

	
	/** ********************************************************************************
	 * core  3/3                                 
	 * 保存\编译。并退出。 保存到数据库=<<achievement>>，保存到文件。返回至列表页
	 *     1.获取题目[PId]，学生学号[SId],[StuNO],获取文件命名[fileName]
	 *     2.获取{editor}的值[value],保存到 表<achie> 的(answer)字段中
	 *     3.把 "StuNO/PId/fileName" 作为路径，保存到 表<achie> 的(answerPath)中
	 *     4.开启线程，调用 #javac 、#java 命令,并获取返回值[return]
	 *     5.将返回值[return] 写入表<achie> 的(result)字段中
	 *     6.比较[return]与 表<itemBank>中的(result)字段是否相等 => - 通过   80
	 *                                          .eqaul()   - 未通过 20  
	 *       存到表<achie>的(score)字段中
	 *     7.返回到&list页面 = "todoexplist.jsf"
	 *  ********************************************************************************
	 */
	String saveCompileExit() {
			return "";
	}

	
	
	/**
	 * 将ExpReport e 赋值给 Achievement a;
	 * 
	 */
	public void merge(ExpReport e,Achievement a){
		a.setAnswer(e.getAnswerText());//答案赋值
		a.setAnswerPath(e.getFilePath());//答案路径赋值
		if(e.getCurrentUser() != null) { a.setStudent(e.getCurrentUser());} //学生赋值
		a.setItemBank(e.getQuestion());//问题赋值
	}
	
	/**
	 * @return the achie
	 */
	public Achievement getAchie() {
		return achie;
	}

	/**
	 * @param achie the achie to set
	 */
	public void setAchie(Achievement achie) {
		this.achie = achie;
	}

	/**
	 * @return the exp
	 */
	public ExpReport getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(ExpReport exp) {
		this.exp = exp;
	}

}
