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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cn.edu.sdut.softlab.controller.ExpReport;
import cn.edu.sdut.softlab.entity.Achievement;
import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;

/**
 * @author GaoYisheng 2017年6月7日 TODO 提交实验报告的服务类
 */
@Stateless
@Named("handInService")
public class HandInService extends AbstractFacade<Achievement> {
	private static Process process ;

//	@ManagedProperty(value = "#{expReport}")
//	private ExpReport er;
	
	public HandInService() {
		super(Achievement.class);
	}

	/**
	 * 2017-06-07
	 * 
	 * @param entityClass
	 */
	public HandInService(Class<Achievement> entityClass) {
		super(entityClass);
	}
	
	@Inject
	EntityManager em;

	@Inject
	@LoggedIn
	private Student currentUser;// 当前用户

	public Student getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Student currentUser) {
		this.currentUser = currentUser;
	}

	@RequestScoped
	@ManagedProperty(value = "#{questionManager.allQuestions}")
	private List<ItemBank> questions;

	public void setQuestions(List<ItemBank> questions) {
		this.questions = questions;
	}

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
	public void saveFile() throws Exception {
		
		//FilePath 以 /home/morpheus/ejosData/为基础， uid/qid为动态值．
		File sourceFile = new File( exp.getFilePath() + exp.getClassName() +".java");//保存源代码  
		
		try {
					
		    if(sourceFile.exists()){  
		      sourceFile.delete();  
		     }  
		
		    FileWriter fr = new FileWriter(sourceFile);  //将文件保存起来
		    BufferedWriter bw = new BufferedWriter(fr);  
		    
		    log.info(exp.getAnswerText());
		    log.info("--------------------------------------------------\n");
		    log.info(exp.getAnswerText().replaceAll("<br>","").replaceAll("&nbsp;",""));
		    log.info("--------------------------------------------------\n");
		    String writeString = exp.getAnswerText().replaceAll("<br>","").replaceAll("&nbsp;","");
		    log.info(writeString);
		    
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
			File sourceFile = new File(exp.getFilePath() + exp.getClassName() + ".class");//如果文件存在，则删除该文件
			if (sourceFile.exists()) {
				log.info("调用" + exp.getClassName() + ".class--sourceFile.delete();");
				sourceFile.delete();
			}
			
			File dir = new File(exp.getFilePath());
			File outputFile = new File(exp.getFilePath()+"output.txt");//如果文件存在，则删除该文件
			if (outputFile.exists()) {
				log.info("调用output.txt--sourceFile.delete();");
				outputFile.delete();
			}	
			
			
			String cmdCompile = "javac " + exp.getClassName() + ".java 2>> output.txt";

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

				File dir = new File(exp.getFilePath());
				
				String cmd = "java " + exp.getClassName() + " >> output.txt";

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

	public String showResult(){
		log.info("调用showResult()");
		//读取文件，并返回到前台
      String result = "";
		try {
			
			File resultFile = new File(exp.getFilePath()+"output.txt");
			if (!resultFile.exists()) {
				//如果文件不存在，创建一个文件
				log.info("调用output.txt--sourceFile.create();");
				resultFile.createNewFile();
			}
			
         InputStreamReader read = new InputStreamReader(new FileInputStream(resultFile));//考虑到编码格式
         BufferedReader bufferedReader = new BufferedReader(read);
         String line = null;
         while((line = bufferedReader.readLine()) != null){
			     result = result +  "\n" + line;
			     log.info(result);
			}
     
     
      read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return result;
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
		return exp;
	}

	/**
	 * @param exp
	 *            the exp to set
	 */
	public void setExp(ExpReport exp) {
		this.exp = exp;
	}

	
	//选择的值，改变listener
   public void selectedChanged(ValueChangeEvent event) {
	   log.info("onSelect is called ------------------");
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", "hah"));
      //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getNewValue().toString()));
   }	
	
	public class RuntimeInput implements Runnable {

		@Override
		public void run() {
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String content = null;
			BufferedWriter bw = null;
			File outputFile = new File("/home/morpheus/ejosData/userid/questionid/output.txt");
			try {
				FileWriter fr = new FileWriter(outputFile);
				bw = new BufferedWriter(fr);

				while ((content = br.readLine()) != null) {

                bw.write(content);				
					//System.out.println(content);// 如果想把结果输出到页面，直接定义变量就行

				}

				bw.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
