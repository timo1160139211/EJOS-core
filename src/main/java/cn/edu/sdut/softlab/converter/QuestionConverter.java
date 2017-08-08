/*
 * file_name: QuestionConverter.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年8月7日 
 *       https://www.gaoyisheng.site
 *       https://github.com/timo1160139211
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.sdut.softlab.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.service.QuestionFacade;

/**
 * @author GaoYisheng 2017年8月7日 TODO
 */
@Named
@FacesConverter(forClass=ItemBank.class)
public class QuestionConverter implements Converter,Serializable{

	private static final long serialVersionUID = -5751815832476762693L;

	@Inject 
	QuestionFacade questionService;

//	@Inject
//	public void setQuestionService(QuestionFacade questionService) {
//		this.questionService = questionService;
//	}

//	@Inject
//	ConverterFacade converterService;

	
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		System.out.println("QuestionConverter:log print >> --------------------String value is " + value);

		if(value == null || value.isEmpty() || value.equals("")){
			return null;
		}
		
//		QuestionFacade questionService = new QuestionFacade();

//		return converterService.findSpecifiedItemBankByQuestion(value);
		return questionService.findSpecifiedItemBankByQuestion(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value instanceof ItemBank){
			System.out.println("QuestionConverter:log print >> --------------------Object value is " + value.toString());
			return String.valueOf((ItemBank) value);
		}else{
			return null;
		}
	}

}
