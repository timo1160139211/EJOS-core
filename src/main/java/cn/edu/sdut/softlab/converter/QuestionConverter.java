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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import cn.edu.sdut.softlab.controller.QuestionManager;
import cn.edu.sdut.softlab.entity.ItemBank;
import cn.edu.sdut.softlab.service.QuestionFacade;

/**
 * @author GaoYisheng 2017年6月17日 TODO
 */
@ManagedBean(name = "questionConverter")
@FacesConverter(value = "questionConverter")
public class QuestionConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

//	@Inject
	QuestionFacade questionService = new QuestionFacade();
	
	@Inject
	FacesContext facesContext;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {

		System.out.println("-----------------------getAsObject-value-is-" + value + "0" + "\0" + "1");

		if (value == null || value.isEmpty()) {
			
			System.out.println("-----------------------1.if ");
			return null;
		}

		if (!value.equals("") && value != null) {
			System.out.println("-----------------------2.if ");
			
			System.out.println(
					"-----------------------" + questionService.name);
			
			
//			//延迟4,000ms
//			for(int i=0;i<99999;i++){
//				for(int j=0;j<999;j++){
//					
//				}
//			}
			
			ItemBank ib = questionService.findSpecifiedItemBankByQuestion(value);
			
			System.out.println(
					"-----------------------" + ib.getId() + " in ib " + ib.getIntroduce());
			
			//return ib.getQuestion();
			return ib;
			
//			return questionService.findSpecifiedItemBankByQuestion(value);
			
//			ItemBank ib = new ItemBank();
//			ib.setId(1024);
//			ib.setQuestion(value);

			
//			return ib0;
		
		}

		System.out.println("-----------------------no.if ");
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

			// return ((ItemBank) value).getQuestion();
			return String.valueOf((ItemBank) value);
		else {
			return null;
		}
	}

}
