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

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.entity.Team;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;

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
	@SessionScoped
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
	
//	@Inject
//	private QuestionFacade questionFacade;
	
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
			
//			logger.info("getQuestions---------------in Manager is calledddd");
//			
//			logger.info(currentUser.getName()+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//			String searchString = currentUser.getTeam().getName();
//			CriteriaBuilder cb = em.getCriteriaBuilder();
//			CriteriaQuery criteria = cb.createQuery();
//			Root<Team> team = criteria.from(Team.class);
//			
//			Query query = em.createQuery(
//					criteria.select(team).where(
//						cb.equal(
//								team.get("name"), 
//								cb.parameter(String.class, "teamName")
//							)
//						)
//					).setParameter("teamName", searchString);
//					
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
	
}

