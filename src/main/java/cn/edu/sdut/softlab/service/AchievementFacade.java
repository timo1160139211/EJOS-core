/*
 * file_name: AchievementFacade.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年8月9日 
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
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import cn.edu.sdut.softlab.entity.Achievement;
import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.entity.Student;

/**
 * @author GaoYisheng 
 * 2017年8月9日
 * TODO
 */
@Named("achievementService")
@RequestScoped
public class AchievementFacade extends AbstractFacade<Achievement> {

	public AchievementFacade() {
		super(Achievement.class);
		System.out.println("log print --------------------------成绩service类 构造器调用");
	}

	public Achievement findByQuestionAndStudent(ItemBank question,Student stu){
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("question", question);
		parameters.put("student", stu);
		return findSingleByNamedQuery("Achievement.findByQuestionAndStudent", parameters, Achievement.class).get();
	}
	
}
