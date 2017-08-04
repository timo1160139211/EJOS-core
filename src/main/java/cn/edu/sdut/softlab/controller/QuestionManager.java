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

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.entity.Team;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;
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
	ItemBank question;
	
	public ItemBank getQuestion() {
		return question;
	}
                                 
	public void setQuestion(ItemBank question) {
		this.question = question;
	}

	@Inject
	@LoggedIn
	private Student currentUser;// 当前用户
	
	@Inject
	private QuestionFacade questionService;
	
	public Student getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Student currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the questions
	 */
	
	@SuppressWarnings({ "unchecked" })
	public List<ItemBank> getAllQuestions() throws Exception {
		try {
			utx.begin();
			logger.info(currentUser.getName()+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			Team paramTeam = currentUser.getTeam();
			Query query = em.createQuery(
					"select q from ItemBank q where q.team = :paramTeam"
					).setParameter("paramTeam", paramTeam);
			
			return query.getResultList();		
		} finally {
			utx.commit();
		}
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItemBank> getAllQuestionsWithoutTeam() throws Exception {
		try {
			utx.begin();
			logger.info("getQuestions---------------in Manager is calledddd");
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(ItemBank.class));
			
			return em.createQuery(cq).getResultList();
			
		} finally {
			utx.commit();
		}
	}
	
	//可以用的 不用service
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemBank getSpecifiedQuestion(String question) throws Exception {
		try {
			utx.begin();
			logger.info("getQuestions---------------in Manager is calledddd");
			
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(ItemBank.class));
			
			return (ItemBank) em.createQuery(cq).getSingleResult();
			
		} finally {
			utx.commit();
		}
	}
	
	//测试Service
	public ItemBank getSpecifiedQuestionByQuestion(String question)throws Exception{
		try{
			utx.begin();
			
			return questionService.findSpecifiedItemBankByQuestion(question);
			
			// TODO Auto-generated catch block
		} finally{
			utx.commit();
		}
		
	}
	
}

