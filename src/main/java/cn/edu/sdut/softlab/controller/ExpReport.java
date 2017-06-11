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

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import cn.edu.sdut.softlab.entity.*;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;

/**
 * @author GaoYisheng 2017年6月5日 TODO 实验报告
 */
@RequestScoped
@Named("expReport")
@Default
public class ExpReport {
	private String fileName;//文件名
	private String result;//返回值
	private String filePath;//文件保存的路径
	private String answerText;//代码(答案)
	
	private ItemBank question;//当前题目
	
	//注入受管bean
	@ManagedProperty(value ="#{login.currentUser}" )
	private Student currentUser ;//当前用户

	@Produces
	@LoggedIn
	public Student getCurrentUser() {
		return currentUser;
	}
	
	public ItemBank getQuestion() {
		return question;
	}

	public void setQuestion(ItemBank question) {
		this.question = question;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
