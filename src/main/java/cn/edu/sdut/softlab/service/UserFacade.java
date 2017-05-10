///*
// * file_name: UserFacade.java 
// *
// * Copyright GaoYisheng Corporation 2017 
// * 
// * License： 
// * date：2017年5月5日 
// *       https://www.gaoyisheng.site
// *       https://github.com/timo1160139211
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package cn.edu.sdut.softlab.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.ejb.Stateless;
//import javax.inject.Named;
//
//import cn.edu.sdut.softlab.entity.Teacher;
//
///**
// * @author GaoYisheng 
// * 2017年5月5日
// * 查询用户
// */
//@Stateless
//@Named("user")
//public class UserFacade extends AbstractFacade<Teacher> {
//
//	public UserFacade() {
//		super(Teacher.class);
//	}
//
//	/**
//	 * 同findByUsernameAndPassword()理
//	 */
//	public Teacher findByName(String name) {
//		Map<String, Object> parameters = new HashMap<>(0);
//		parameters.put("name", name);
//		return findSingleByNamedQuery("Teacher.findByUsername", parameters, Teacher.class).get();
//	}
//	
//	/**
//	 * 同findByUsernameAndPassword()理
//	 * 
//	 * 以后 找回密码用：mail + pwd
//	 */
///*	public Student findByMail(String mail) {
//		Map<String, Object> parameters = new HashMap<>(0);
//		parameters.put("mail", mail);
//		*/
//		/*
//		 * 待 修改完善
//		 * findSingleByNamedQuery => findSingleByMailQuery
//		 */
///*		return findSingleByNamedQuery("Stuff.findByUsername", parameters, Student.class).get();
//	}*/
//	
//
///*	public Teacher findByUsernameAndPassword(String username, String password) {
//		Map<String, Object> parameters = new HashMap<>(0);
//		parameters.put("username", username);
//		parameters.put("password", password);
//		return findSingleByNamedQuery("Teacher.findByUsernameAndPassword", parameters, Teacher.class).get();
//	}*/
//}
