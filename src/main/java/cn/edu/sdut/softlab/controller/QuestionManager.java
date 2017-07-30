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
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.entity.Team;

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

	//注入受管bean
	@ManagedProperty(value ="#{login.currentUser}" )
	@SessionScoped
	private Student currentUser ;//当前用户

//	@Inject
//	private QuestionFacade questionFacade;
	
	/**
	 * @return the questions
	 */
	
	@SuppressWarnings({ "unchecked" })
	@Produces
	@Named
	@RequestScoped
	public List<ItemBank> getAllQuestions() throws Exception {
		try {
			utx.begin();
			logger.info("getQuestions---------------in Manager is calledddd");
//			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//			cq.select(cq.from(ItemBank.class));
			
//			Team t = currentUser.getTeam();
//			String query = "select i from ItemBank i where i.team = t";
//			return em.createQuery(cq).getResultList();
			return em.createQuery("SELECT i FROM ItemBank i ").getResultList();
		} finally {
			utx.commit();
		}
	}
}

