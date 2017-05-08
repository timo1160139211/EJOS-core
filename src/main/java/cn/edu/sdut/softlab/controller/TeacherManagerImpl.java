/*
 * file_name: TeacherManagerImpl.java 
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Any;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.entity.Teacher;
import cn.edu.sdut.softlab.service.UserFacade;

/**
 * @author GaoYisheng 
 * 2017年5月6日
 * TODO teacherManager 实现
 */
public class TeacherManagerImpl extends AbstractManager<Teacher> implements TeacherManager{


	@Inject @Any
	private transient Logger logger;
	@Inject
	UserFacade userService;

	@Inject
	Credentials credentials;
	
	@Inject
	private UserTransaction utx;

	@Inject
	EntityManager em;
	
	@Inject
	FacesContext facesContext;
	
	private Teacher newTeacher = new Teacher();

	public Teacher getNewTeacher() {
		return newTeacher;
	}

	public void setNewTeacher(Teacher newTeacher) {
		this.newTeacher = newTeacher;
	}
	
	/* (non-Javadoc)
	 * @see cn.edu.sdut.softlab.controller.TeacherManager#getTeacher()
	 */
	@Override
	public List<Teacher> getTeacher() throws Exception {
		try {
			utx.begin();
			return userService.findAll();
		} finally {
			utx.commit();
		}
	}

	/* (non-Javadoc)
	 * @see cn.edu.sdut.softlab.controller.TeacherManager#addTeacher()
	 */
	@Override
	public String addTeacher() throws Exception {
		try {
			utx.begin();
			userService.create(newTeacher);
			logger.log(Level.INFO, "Added {0}", newTeacher);
			return "/index.xhtml?faces-redirect=true";
		} finally {
			utx.commit();
		}
	}

	/* (non-Javadoc)
	 * @see cn.edu.sdut.softlab.controller.TeacherManager#deleteTeacher()
	 */
	@Override
	public String deleteTeacher() throws Exception {
		Teacher temporstuff = userService.findByName(credentials.getUsername());
		if (temporstuff != null) {
/*			currentstuff = temporstuff;*/
		
			utx.begin();
			userService.remove(temporstuff);
			
			utx.commit();
			logger.log(Level.INFO, "Added {0}");
		
			return "/index.xhtml?faces-redirect=true";
		
		}else{

			return "/error.xhtml?faces-redirect=true";
		
		}
	}

}
