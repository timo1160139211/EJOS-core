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
	
	//To insert
//	public void insertSave() {
//		Map<String, Object> parameters = new HashMap<>(0);
//		parameters.put("id", id);
//		return findSingleByNamedQuery("Achievement.insertsave", parameters, Achievement.class).get();
//	}

}
