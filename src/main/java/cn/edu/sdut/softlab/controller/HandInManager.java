/*
 * file_name: HandInManager.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年6月6日 
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

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.service.HandInService;

/**
 * @author GaoYisheng 2017年6月6日 TODO 提交实验报告的管理类
 */
@SessionScoped
@Named("handInManager")
public class HandInManager implements Serializable {

	private static final long serialVersionUID = 7965455427888195913L;

	@Inject
	private transient Logger logger;

	@Inject
	private UserTransaction utx;

	@Inject
	EntityManager em;

	// 注入受管bean
	@ManagedProperty(value = "#{login.currentUser}")
	@SessionScoped
	private Student currentUser;// 当前用户

	@Inject
	FacesContext facesContext;

	@Inject
	private HandInService handInService;


//	void compile() {
//
//		handInService.compileJava();
//
//	}
//	
//	void compile(String fileType) {
//
//		handInService.compileJava();
//
//	}

	String saveCompileBack() {

		return handInService.saveCompileExit();
	}

}
