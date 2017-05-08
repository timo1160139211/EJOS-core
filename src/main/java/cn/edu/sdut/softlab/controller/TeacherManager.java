/*
 * file_name: TeacherManager.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月6日 
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

import java.util.List;

import cn.edu.sdut.softlab.entity.Teacher;


/**
 * @author GaoYisheng 
 * 2017年5月6日
 * TODO 作为teacher user manager interface
 */
public interface TeacherManager {
	
	/**
	 * 获得所有 老师 列表.
	 *
	 * @return 所有老师列表
	 * @throws Exception
	 */
	List<Teacher> getTeacher() throws Exception;

	/**
	 * 新增老师.
	 *
	 * @return 返回老师列表页面
	 * @throws Exception
	 */
	String addTeacher() throws Exception;

	
	/**
	 * 删除老师.
	 *
	 * @author gaoyisheng 2017-02-14
	 * 	@throws Exception
	 */
	public String deleteTeacher() throws Exception;
	

}
