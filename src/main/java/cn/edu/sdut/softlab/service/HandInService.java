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

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import cn.edu.sdut.softlab.controller.ExpReport;
import cn.edu.sdut.softlab.entity.Achievement;

/**
 * @author GaoYisheng 2017年6月7日 TODO 提交实验报告的服务类
 */
@Stateless
@Named("handIn")
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

	// @NamedQuery(name = "Achievement.insertSave", query = "insert into
	// Achievement (question_id,student_id,answer,answer_path) values
	// (:qid,:sid,:answer,:answPath)"),

	// To insert
	// 传入一个ExpReport的对象
	// public void insertSave(int sid,int pid,String path) {
	// Map<String, Object> parameters = new HashMap<>(0);
	// parameters.put("sid", sid);//:qid,:sid,:answer,:answPath
	// parameters.put("qid", pid);
	// parameters.put("answer", id);
	// parameters.put("answPath", p);
	// return findSingleByNamedQuery("Achievement.insertSave", parameters,
	// Achievement.class);
	// }

	public void insertSave2(ExpReport exp) {

	}
}
