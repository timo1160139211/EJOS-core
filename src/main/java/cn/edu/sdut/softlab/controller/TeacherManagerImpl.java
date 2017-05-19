/*
 * file_name: TeacherManagerImpl.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月17日 
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

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.entity.Teacher;
import cn.edu.sdut.softlab.service.TeacherFacade;

/**
 * @author GaoYisheng 
 * 2017年5月17日
 * TODO
 */
@Named("teacherManager")
@RequestScoped
public class TeacherManagerImpl implements TeacherManager {

	@Inject
	private transient Logger logger;
	
	@Inject
	TeacherFacade teacherService;

	@Inject
	Credentials credentials;
	
	@Inject
	private UserTransaction utx;

	@Inject
	EntityManager em;
	
	@Inject
	FacesContext facesContext;
	
/*	private Stuff currentstuff = null;*/
	
	
	private Teacher newTeacher = new Teacher();

	public Teacher getNewTeacher() {
		return newTeacher;
	}

	public void setNewTeacher(Teacher t) {
		this.newTeacher = t;
	}

	
	/* (non-Javadoc)
	 * @see cn.edu.sdut.softlab.controller.TeacherManager#getTeacher()
	 */
	@Override
	public List<Teacher> getTeacher() throws Exception {
		// TODO Auto-generated method stub
		try {
			utx.begin();
			return teacherService.findAll();
		} finally {
			utx.commit();
		}
	}

	/* (non-Javadoc)
	 * @see cn.edu.sdut.softlab.controller.TeacherManager#addTeacher()
	 */
	@Override
	public String addTeacher() throws Exception {
		// TODO Auto-generated method stub
		try {
			utx.begin();
			teacherService.create(newTeacher);
			logger.log(Level.INFO, "Added {0}", newTeacher);
			return "management.xhtml?faces-redirect=true";
		} finally {
			utx.commit();
		}
	}

	/* (non-Javadoc)
	 * @see cn.edu.sdut.softlab.controller.TeacherManager#deleteTeacher()
	 */
	@Override
	public String deleteTeacher() throws Exception {
		// TODO Auto-generated method stub
		Teacher temporstuff = teacherService.findByTeaNO(credentials.getNO());
		if (temporstuff != null) {
/*			currentstuff = temporstuff;*/
		
			utx.begin();
			teacherService.remove(temporstuff);
			
			utx.commit();
			logger.log(Level.INFO, "Added {0}");
		
			return "/management.xhtml?faces-redirect=true";
		
		}else{

			return "/error.xhtml?faces-redirect=true";
		
		}
	}

}
