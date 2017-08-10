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
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.entity.Team;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;
import cn.edu.sdut.softlab.qualifiers.Selected;
import cn.edu.sdut.softlab.service.AchievementFacade;
import cn.edu.sdut.softlab.controller.ExpReport;
import cn.edu.sdut.softlab.entity.Achievement;
import cn.edu.sdut.softlab.entity.ItemBank;

/**
 * @author GaoYisheng 2017年6月6日 TODO 提交实验报告的管理类
 */
@SessionScoped
@Named("handInManager")
public class HandInManager implements Serializable {
//	private static Process process ;
	private static final long serialVersionUID = 7965455427888195913L;

	@Inject
	private transient Logger log;

	@Inject
	EntityManager em;

	@Inject
	AchievementFacade achievementService;
	
	@Inject
	private UserTransaction utx;
	
	@Inject
	@LoggedIn
	private Student currentUser;// 当前用户

	@Inject
	FacesContext facesContext;

	public Student getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Student currentUser) {
		this.currentUser = currentUser;
	}

	ItemBank currentQuestion;
	
	@RequestScoped
	@ManagedProperty(value = "#{questionManager.allQuestions}")
	private List<ItemBank> questions;

	public void setQuestions(List<ItemBank> questions) {
		this.questions = questions;
	}


	public ItemBank getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(ItemBank currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	@Inject
	@SessionScoped
	private ExpReport expReport;

	public ExpReport getExpReport() {
		return expReport;
	}

	public void setExpReport(ExpReport expReport) {
		this.expReport = expReport;
	}

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
	public void saveFile() throws Exception {
		
		//FilePath 以 /home/morpheus/ejosData/为基础， uid/qid为动态值．
		File dirFile = new File(constitutePath());
	   if(!dirFile.exists()){  //如果目录不存在
			dirFile.mkdirs();
		} 
		File sourceFile = new File( constitutePath() + expReport.getClassName() +".java");//保存源代码  
		
		try {
					
		    if(sourceFile.exists()){  
		      sourceFile.delete();  
		     }  
		
		    FileWriter fr = new FileWriter(sourceFile);  //将文件保存起来
		    BufferedWriter bw = new BufferedWriter(fr);  
		    
		     //除标签
		    String writeString = expReport.getAnswerText().replaceAll("<br>","").replaceAll("&nbsp;","");
		    
		    bw.write(writeString);//将获取的代码内容存到文件中  
		    bw.close();  
		    fr.close();  

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}//save done
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

		return "../student/home.xhtml?faces-redirect=true";
	}

	/**
	 * just compile:
	 * 
	 */
	public void compileJava(){
		
		log.info("调用compileJava()");
		Runtime runtime = Runtime.getRuntime();  
		try {
			File sourceFile = new File(constitutePath() + expReport.getClassName() + ".class");//如果文件存在，则删除该文件
			if (sourceFile.exists()) {
				log.info("调用" + expReport.getClassName() + ".class--sourceFile.delete();");
				sourceFile.delete();
			}
			
			File dir = new File(constitutePath());
			File outputFile = new File(constitutePath()+"output.txt");//如果文件存在，则删除该文件
			if (outputFile.exists()) {
				log.info("调用output.txt--sourceFile.delete();");
				outputFile.delete();
			}	
			
			
			String cmdCompile = "javac " + expReport.getClassName() + ".java 2>> output.txt";

			String[] cmdarray = {
					"/bin/sh",
					"-c",
					cmdCompile
					};	
			log.info("调用"+ cmdarray);
			try {
				runtime.exec(cmdarray,null,dir).waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
							
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	}
	
	public void runJava(){

		log.info("调用runJava()");
		Runtime runtime = Runtime.getRuntime();  
			try {

				File dir = new File(constitutePath());
				
				String cmd = "java " + expReport.getClassName() + " >> output.txt";

				String[] cmdarray = {
						"/bin/sh",
						"-c",
						cmd
						};	
				log.info("调用"+ cmdarray.toString());
				runtime.exec(cmdarray,null,dir).waitFor();


			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void showResult()throws Exception {
		log.info("调用showResult()");
		//读取文件，并返回到前台
      String result = "";
		try {
			
			File resultFile = new File(constitutePath()+"output.txt");
			if (!resultFile.exists()) {
				//如果文件不存在，创建一个文件
				resultFile.createNewFile();
			}
			
         InputStreamReader read = new InputStreamReader(new FileInputStream(resultFile));//考虑到编码格式
         BufferedReader bufferedReader = new BufferedReader(read);
         String line = null;
         while((line = bufferedReader.readLine()) != null){
			     result = result + "\n" + line;
			     log.info(result);
			}
//         result.replaceFirst("\n","");//移除第一行前多的"\n"   未生效？！
         
      read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		expReport.setResult(result);//返回，并由button刷新到前台
		
		try {
			utx.begin();
			
			Achievement a = achievementService.findByQuestionAndStudent(currentQuestion, currentUser);
			//如果存在（查询到）该条目，则更新
			if(!(a==null)){
				
				a.setAnswer(expReport.getAnswerText());
				a.setResult(result);
				a.setScore(calculateScore(result,currentQuestion.getResult()));
				
			   achievementService.edit(a);
			//否则创建
		   }else{
			   Achievement ach=new Achievement(
						expReport.getAnswerText(),
						constitutePath(), result, 
						calculateScore(result,currentQuestion.getResult()),
						currentQuestion, currentUser);
			   achievementService.create(ach);
		   	}
		} finally {
			utx.commit();
		}
		
		}
		
	
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
		return expReport;
	}

	/**
	 * @param exp
	 *            the exp to set
	 */
	public void setExp(ExpReport exp) {
		this.expReport = exp;
	}
	
	public void selectedChanged(ValueChangeEvent event) {
	   System.out.println("logPrint >> ---------------QuestionManager-selectedChanged-value-is:" + event.getNewValue().toString());
	   facesContext.addMessage(null, new FacesMessage("当前问题是： " + event.getNewValue().toString()));
	}
	
	private String constitutePath(){
		String dir="/home/morpheus/ejosData/";
		String path=dir+currentUser.getId()+"/"+currentQuestion.getId()+"/";		
		return path;
	}
	
	//计算成绩的 算法，是什么好呢？
	private int calculateScore(String result,String answerResult){
		//每一行答案在读取时多一个文件结束符EOF?  \0? -1?      = 不多！  前面多一个\n ！
		answerResult="\n"+answerResult;
		
		log.info("-------------------------------------------------" + result + "===" + result.length());
		log.info("-------------------------------------------------" + answerResult + "===" + answerResult.length());

		if(result.equals(answerResult)){
			return 80;
		}
		else{
			return 50;
		}
		
	}
}
