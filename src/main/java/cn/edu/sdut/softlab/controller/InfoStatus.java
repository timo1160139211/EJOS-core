/*
 * file_name: InfoStatus.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月27日 
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

/**
 * @author GaoYisheng 
 * 2017年5月27日
 * TODO
 */
public enum InfoStatus {

	AVALIABLE("已读"),
	NOT_AVALIABLE("未读");
	
	private String status;
	
	public String getStatus(){
		return status;
	}
	
	private InfoStatus(String s){
		this.status=s;
	}
}
