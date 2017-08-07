/*
 * file_name: QuestionFacade.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年8月7日 
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
import javax.inject.Named;

import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.entity.Team;

/**
 * @author GaoYisheng 
 * 2017年8月7日 
 * TODO 问题服务类
 */
@Stateless
@Named
public class QuestionFacade extends AbstractFacade<ItemBank> {
	
	public QuestionFacade(){
		super(ItemBank.class);
		System.out.println("log print --------------------------问题service类 构造器调用");
	}

	public String name = "i am question service .";

	/**
	 * 2017-08-07
	 * @param question
	 * @return
	 */
	public ItemBank findSpecifiedItemBankByQuestion(String question) {
		System.out.println("log print ----------------findSpecifiedItemBankByQuestion is called");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("question", question);
		return findSingleByNamedQuery("ItemBank.findByQuestion", parameters, ItemBank.class).get();
	}

	/**
	 * 2017-08-07
	 * @return
	 */
	public List<ItemBank> findAllQuestionsWithoutTeam() {
		System.out.println("Question Facade:log print ----------------findAllQuestionWithoutTeam is called\n");
		return findAll();
	}

	/**
	 * 2017-08-07
	 * @param paramTeam
	 * @return
	 */
	public List<ItemBank> findAllQuestionsWithTeam(Team paramTeam) {
		System.out.println("log print ----------------findSpecifiedItemBankByQuestion is called");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("team", paramTeam);
		return findByNamedQuery("ItemBank.findByTeam", parameters);
	}

	
}
