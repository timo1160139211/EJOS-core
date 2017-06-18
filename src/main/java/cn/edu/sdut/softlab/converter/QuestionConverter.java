/*
 * file_name: Questionconverter.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年6月17日 
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

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.service.QuestionFacade;

/**
 * @author GaoYisheng 2017年6月17日 TODO
 */
@ManagedBean(name = "questionConverter")
@FacesConverter(value = "questionConverter")
@RequestScoped
public class QuestionConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	QuestionFacade questionService;

	@Inject
	FacesContext facesContext;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String question) throws ConverterException {

		if (question == null || question.isEmpty()) {
			return null;
		}
		
		if (!question.equals("") && question != null) {

			// 根据名字查找到对象 不行
			/*
			 * Category category =
			 * categoryservice.findSpecifiedCategoryByName(name); return
			 * category.getName();
			 */

			// 法二,直接
			return questionService.findSpecifiedItemBankByQuestion(question);

		}

		return null;
	}

	/**
	 * @param value
	 * 
	 * @return String
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		
		if (value instanceof ItemBank)

			return ((ItemBank) value).getQuestion();

		else {
			return null;
		}
	}

}
