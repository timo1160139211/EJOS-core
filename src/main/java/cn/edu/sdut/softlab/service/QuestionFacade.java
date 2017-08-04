/*
 * file_name: QuestionFacade.java 
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
package cn.edu.sdut.softlab.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cn.edu.sdut.softlab.entity.ItemBank;

/**
 * @author GaoYisheng 2017年6月11日 TODO 题目服务类
 */
@Stateless
@Named("item_bank")
public class QuestionFacade extends AbstractFacade<ItemBank> {

	@Inject EntityManager em;
	
	public String name="questionService";
	
	public QuestionFacade() {
		super(ItemBank.class);
		System.out.println("QuestionFacade is called----------------------- \n\n\n");
	}

	/**
	 * 2017-06-11
	 * 
	 * @param entityClass
	 */
	public QuestionFacade(Class<ItemBank> entityClass) {
		super(entityClass);
		System.out.println("QuestionFacade Class<ItemBank> entityClass is called----------------------- \n\n\n");
	}

	public List<ItemBank> findAllQuestion() {
		return findAll();
	}

	/*
	 * 查找指定 Question,服务于 converter
	 */
/*	public ItemBank findSpecifiedItemBankByQuestion(String question) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("question", question);
		return findSingleByNamedQuery("ItemBank.findByQuestion", parameters, ItemBank.class).get();
	}*/

	//为converter服务
//	public ItemBank findSpecifiedItemBankByQuestion(String question) {
//		String queryString = "SELECT i FROM ItemBank i WHERE i.question=:question";
//		Query query = em.createQuery(queryString);
//		query.setParameter("question", question);
//		return (ItemBank) query.getSingleResult();
//	}
	
	public ItemBank findSpecifiedItemBankByQuestion(String question) {
    	System.out.println("findSpecifiedItemBankByQuestion  --  is called \n\n\n\n");
		
    	int id = 1;
    	return (ItemBank) em.createQuery("SELECT i FROM ItemBank i " + 
    								"WHERE i.id=:id")
    			.setParameter("id", id)
    			.getSingleResult();
    }
	
	
	public ItemBank findSpecifiedItemBankById(int id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		return findSingleByNamedQuery("ItemBank.findByQid", parameters, ItemBank.class).get();
	}

	// 查找题目列表
	// public List<ItemBank> findQuestionsListForTeam(Team t) {
	// Map<String, Object> parameters = new HashMap<>(0);
	// parameters.put("team", t);
	// return findByNamedQuery("ItemBank.findByTeam", parameters);
	//
	// }

	public List<ItemBank> findQuestionsListForTeam(int teamId) {
		Map<String, Object> parameters = new HashMap<>(0);
		parameters.put("teamId", teamId);
		return findByNamedQuery("ItemBank.findByTeamId", parameters);

	}
}
