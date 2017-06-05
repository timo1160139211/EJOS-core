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
import javax.inject.Named;

/**
 * @author GaoYisheng 2017年6月5日 TODO 实验报告
 */
@RequestScoped
@Named
@Default
public class ExpReport {

	private String result;
	private String answerPath;
	private String answertext;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAnswerPath() {
		return answerPath;
	}

	public void setAnswerPath(String answerPath) {
		this.answerPath = answerPath;
	}

	public String getAnswertext() {
		return answertext;
	}

	public void setAnswertext(String answertext) {
		this.answertext = answertext;
	}
}
