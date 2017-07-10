/*
 * file_name: HandInService.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年6月7日 
 *       https://www.gaoyisheng.site
 *       https://github.com/timo1160139211
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.sdut.softlab.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cn.edu.sdut.softlab.controller.ExpReport;
import cn.edu.sdut.softlab.entity.Achievement;
import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.entity.Student;

/**
 * @author GaoYisheng 2017年6月7日 TODO 提交实验报告的服务类
 */
@Stateless
@Named("handInService")
public class HandInService extends AbstractFacade<Achievement> {
	
	public HandInService() {
		super(Achievement.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 2017-06-07
	 * 
	 * @param entityClass
	 */
	public HandInService(Class<Achievement> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	

	@Inject
	EntityManager em;

	@ManagedProperty(value = "#{login.currentUser}")
	@SessionScoped
	private Student currentUser;// 当前用户
	
	@RequestScoped
	@ManagedProperty(value = "#{questionManager.questions}")
	private List<ItemBank> questions;


	@Inject
	@SessionScoped
	private ExpReport exp;
	/*
	 * String fileName;//文件名 String result;//返回值 String filePath;//文件保存的路径
	 * String answerText;//代码(答案)
	 */

	private Achievement achie;
	/*
	 * int id; String answer; String answerPath; String result; int score;
	 * ItemBank itemBank; Student student;
	 */

	/**
	 * *********************************************************************************
	 * core 1/3 保存编辑内容 到数据库=<<achievement>>
	 * 1.获取题目[PId]，学生学号[SId],获取文件命名[fileName] 
	 * 2.获取{editor}的值[value],保存到 表<achie>的 (answer)字段中 
	 * 3.把 "SId/PId/fileName" 作为路径，保存到 表<achie> 的(answerPath)中 
	 * 4.
	 * *********************************************************************************
	 */
	void save() throws Exception {
		// 将路径加上参数配置成全路径 /data/ejos/exp/SId/PId/fileName
		String path = "/data/ejos/exp/" + currentUser.getId() + "/" + exp.getQuestion().getId() + "/";
		exp.setFilePath(path);
		//merge(exp, achie);// 将exp赋值给achie
			
		try {
			
			File sourceFile = new File(exp.getFilePath() + exp.getClassName() +".java");//保存源代码  
		     if(sourceFile.exists()){  
		      sourceFile.delete();  
		     }  
		     FileWriter fr = new FileWriter(sourceFile);  //将文件保存起来
		     BufferedWriter bw = new BufferedWriter(fr);  
		     bw.write(exp.getAnswerText());//将获取的代码内容存到文件中  
		     bw.close();  
		     fr.close();  

		}  catch (IOException e) {
			e.printStackTrace();
			
		}finally {
			
		}

	}

	/**
	 * *********************************************************************************
	 * core 2/3 保存并编译。保存到数据库=<<achievement>>，保存到文件
	 * 1.获取题目[PId]，学生学号[SId],[StuNO],获取文件命名[fileName] 
	 * 2.获取{editor}的值[value],保存到表<achie> 的(answer)字段中 
	 * 3.把 "StuNO/PId/fileName" 作为路径，保存到 表<achie>的(answerPath)中 
	 * 4.开启线程，调用 #javac 、#java 命令,并获取返回值[return] 
	 * 5.将返回值[return]写入表<achie> 的(result)字段中 
	 * 6.比较[return]与 表<itemBank>中的(result)字段是否相等 => - 通过80 .eqaul() 
	 *                                                              - 未通过 20  
	 *                  存到表<achie>的(score)字段中 
	 * 7.
	 * *********************************************************************************
	 *
	 * 0x01:把编译结果返回到页面，得到通过与否
	 */
	void saveAndCompile() {
		
			// 将路径加上参数配置成全路径 /data/ejos/exp/SId/PId/fileName
			String path = "/data/ejos/exp/" + currentUser.getId() + "/" + exp.getQuestion().getId() + "/";
			exp.setFilePath(path);
			//merge(exp, achie);// 将exp赋值给achie
			File sourceFile = new File(exp.getFilePath() + exp.getClassName() +".java");//保存源代码  
				
			try {
				
				
			     if(sourceFile.exists()){  
			      sourceFile.delete();  
			     }  
			     FileWriter fr = new FileWriter(sourceFile);  //将文件保存起来
			     BufferedWriter bw = new BufferedWriter(fr);  
			     bw.write(exp.getAnswerText());//将获取的代码内容存到文件中  
			     bw.close();  
			     fr.close();  

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				
			}//save done
			
			//compile
			try {
			Runtime runtime = Runtime.getRuntime();  
		     	
				runtime.exec("javac "+ sourceFile );
				
			} catch (IOException e) {
				e.printStackTrace();
			}  
			
		     //获取控制台输出的结果  
		     Thread runtimeInput = new Thread(new RuntimeInput());  
		     runtimeInput.start();  

	}

	/**
	 * ********************************************************************************
	 * core 3/3 保存\编译。并退出。 保存到数据库=<<achievement>>，保存到文件。返回至列表页
	 * 1.获取题目[PId]，学生学号[SId],[StuNO],获取文件命名[fileName] 
	 * 2.获取{editor}的值[value],保存到表<achie> 的(answer)字段中 
	 * 3.把 "StuNO/PId/fileName" 作为路径，保存到 表<achie>的(answerPath)中 
	 * 4.开启线程，调用 #javac 、#java 命令,并获取返回值[return] 
	 * 5.将返回值[return]写入表<achie> 的(result)字段中 
	 * 6.比较[return]与 表<itemBank>中的(result)字段是否相等 => - 通过80 .eqaul() 
	 *                                                           - 未通过 20 存到表<achie>的(score)字段中 
	 * 7.返回到&list页面 ="todoexplist.jsf"
	 * ********************************************************************************
	 *
	 * 0x01:不把编译结果返回到页面，直接跳转到OK页面
	 */
	public String saveCompileExit() {
		
		// 将路径加上参数配置成全路径 /data/ejos/exp/SId/PId/fileName
		String path = "/data/ejos/exp/" + currentUser.getId() + "/" + exp.getQuestion().getId() + "/";
		exp.setFilePath(path);
		//merge(exp, achie);// 将exp赋值给achie
		File sourceFile = new File(exp.getFilePath() + exp.getClassName() +".java");//保存源代码  
			
		try {
			
			
		     if(sourceFile.exists()){  
		      sourceFile.delete();  
		     }  
		     FileWriter fr = new FileWriter(sourceFile);  //将文件保存起来
		     BufferedWriter bw = new BufferedWriter(fr);  
		     bw.write(exp.getAnswerText());//将获取的代码内容存到文件中  
		     bw.close();  
		     fr.close();  

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}//save done
		
		//compile
		try {
		Runtime runtime = Runtime.getRuntime();  
	     	
			runtime.exec("javac "+ sourceFile );
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
	     //获取控制台输出的结果  
	     Thread runtimeInput = new Thread(new RuntimeInput());  
	     runtimeInput.start();  

		
		
		return "../student/home.xhtml?faces-redirect=true";
	}

	
	/**
	 * just compile:
	 * 
	 */
	public void compileJavaBackUp(){
		
		


		Runtime runtime = Runtime.getRuntime();  
			try {
				runtime.exec("javac /home/morpheus/ejosData/userid/questionid/HelloWorld.java");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
	     //获取控制台输出的结果  
//	     Thread runtimeInput = new Thread(new RuntimeInput());  
//	     runtimeInput.start();  

		
	}
	
	/**
	 * just compile:
	 * 
	 */
	public void compileJava(){
		
		Runtime runtime = Runtime.getRuntime();  
			try {
				runtime.exec("javac /home/morpheus/ejosData/userid/questionid/HelloWorld.java");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
	     //获取控制台输出的结果  
	     Thread runtimeInput = new Thread(new RuntimeInput());  
	     runtimeInput.start();  

		
	}
	
	
	
	
	
	
	/**
	 * 将ExpReport e 赋值给 Achievement a;
	 * 
	 */
/*	public void merge(ExpReport e, Achievement a) {
		a.setAnswer(e.getAnswerText());// 答案赋值
		a.setAnswerPath(e.getFilePath());// 答案路径赋值
		if (e.getCurrentUser() != null) {
			a.setStudent(e.getCurrentUser());
		} // 学生赋值
		a.setItemBank(e.getQuestion());// 问题赋值
	}
*/
	/**
	 * @return the achie
	 */
	public Achievement getAchie() {
		return achie;
	}

	/**
	 * @param achie
	 *            the achie to set
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
	 * @param exp
	 *            the exp to set
	 */
	public void setExp(ExpReport exp) {
		this.exp = exp;
	}


}
