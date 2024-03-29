/*
 * file_name: ExpReport.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年6月5日 
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

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cn.edu.sdut.softlab.entity.*;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;

import java.io.Serializable;

/**
 * @author GaoYisheng 2017年6月5日 TODO 实验报告
 */
@SessionScoped
@Named("expReport")
public class ExpReport implements Serializable {
	private static final long serialVersionUID = 714155121L;

	@Inject
	FacesContext facesContext;

	private String className;// 文件名
	private String result;// 返回值
	private String filePath;// 文件保存的路径
	private String answerText;// 代码(答案)

	@PostConstruct
	public void init(){
		this.answerText = "Please coding here ...";
		this.filePath = "/home/morpheus/ejosData/userid/questionid/";
	}

	@Inject
	@LoggedIn
	private Student currentUser;// 当前用户

	public Student getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Student currentUser) {
		this.currentUser = currentUser;
	}

	public String getClassName() {
		// 临时变量
		String classStr = answerText.substring(answerText.indexOf("public class"), answerText.indexOf("{")).toString();// 获取类名字符串
		String[] classStrArray = classStr.split("\\s{1,}");// 按空格分开
		if (classStrArray.length != 3) {

			facesContext.addMessage(null, new FacesMessage("编译失败：格式不符合规范，请检查类名是否正确 !(如：public class ClassName{}) 错误来源=>" + classStrArray.toString()));
		} else {
			className = classStrArray[classStrArray.length - 1];

		}
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
