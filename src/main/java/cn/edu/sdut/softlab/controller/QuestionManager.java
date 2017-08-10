/*
 * file_name: QuestionManager.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年6月11日 
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
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.entity.Team;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;
import cn.edu.sdut.softlab.qualifiers.Selected;
import cn.edu.sdut.softlab.service.QuestionFacade;

/**
 * @author GaoYisheng 
 * 2017年6月11日
 * TODO 题目管理类
 */
@SessionScoped
@Named("questionManager")
public class QuestionManager implements Serializable{

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
	QuestionFacade questionService;
	
//	@Inject
//	private ItemBank currentQuestion;//=null;
//	
//	@Produces
//	@Selected
//	public ItemBank getCurrentQuestion() {
//		return currentQuestion;
//	}
//
//	public void setCurrentQuestion(ItemBank ib) {
//		currentQuestion = ib;
//	}

	@Inject
	@LoggedIn
	private Student currentUser;// 当前用户
	
	public Student getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Student currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the questions
	 */
	public List<ItemBank> getAllQuestionsWithTeam() throws Exception {
		try {
			utx.begin();
			Team paramTeam = currentUser.getTeam();
			return questionService.findAllQuestionsWithTeam(paramTeam);
		} finally {
			utx.commit();
		}
	}

	public List<ItemBank> getAllQuestionsWithoutTeam() throws Exception {
		try {
			utx.begin();
			logger.info("getAllQuestionsWithoutTeam---------------in Manager is calledddd");
			
			return questionService.findAllQuestionsWithoutTeam();
			
		} finally {
			utx.commit();
		}
	}
	
	/**
	 * 处理当前问题值改变逻辑.
	 */
	   public void selectedChanged(ValueChangeEvent event) {
		   System.out.println("logPrint >> ---------------QuestionManager-selectedChanged-value-is:" + event.getNewValue().toString());
			
			String introduce = questionService.findSpecifiedItemBankByQuestion(event.getNewValue().toString()).getIntroduce();
		   facesContext.addMessage(null, new FacesMessage("当前问题是： " + event.getNewValue().toString()));
		   facesContext.addMessage(null, new FacesMessage("题目要求： " + introduce));
	   }
		
}

