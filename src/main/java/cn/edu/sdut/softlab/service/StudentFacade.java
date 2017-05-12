/*
 * file_name: StudentFacade.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月10日 
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

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;

import cn.edu.sdut.softlab.entity.Student;;

/**
 * @author GaoYisheng 2017年5月10日 TODO
 */
@Stateless
@Named("studentfacade")
public class StudentFacade extends AbstractFacade<Student> {
	
	public StudentFacade() {
		super(Student.class);
	}

	/**
	 * 2017-05-10
	 * 
	 * @param entityClass
	 */
//	public StudentFacade(Class<Student> entityClass) {
//		super(entityClass);
//		// TODO Auto-generated constructor stub
//	}

	public Student findByStuNO(String stuNO) {
		Map<String, Object> parameters = new HashMap<>(0);
		parameters.put("stuNO", stuNO);
		return findSingleByNamedQuery("Student.findByStuNO", parameters, Student.class).get();
	}

	/**
	 * 2017-05-10
	 * 
	 * @param stuNO
	 * @param password
	 * @return
	 */
	public Student findByStuNOAndPassword(BigInteger stuNO, String password) {

		Map<String, Object> parameters = new HashMap<>(0);
		parameters.put("stuNO", stuNO);
		parameters.put("password", password);
		return findSingleByNamedQuery("Student.findByStuNOAndPassword", parameters, Student.class).get();
	}

}
