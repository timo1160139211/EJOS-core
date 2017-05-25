/*
 * file_name: EditorView.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月25日 
 *       https://www.gaoyisheng.site
 *       https://github.com/timo1160139211
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.sdut.softlab.entity;

import javax.faces.bean.ManagedBean;

/**
 * @author GaoYisheng 
 * 2017年5月25日
 * TODO primefaces 编辑器
 */
@ManagedBean
public class EditorView {
    private String text;
    
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
    }
}
