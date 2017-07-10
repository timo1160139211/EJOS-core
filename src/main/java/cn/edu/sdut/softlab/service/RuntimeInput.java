/*
 * file_name: RuntimeInput.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年6月26日 
 *       https://www.gaoyisheng.site
 *       https://github.com/timo1160139211
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.sdut.softlab.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author GaoYisheng 
 * 2017年6月26日
 * TODO
 */
public class RuntimeInput implements Runnable {

	private static Process process ;//执行子进程  b不知道这样写好是嗯对不对
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		   BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
		   String content = null;
		   try {  
		    while((content = br.readLine()) != null){  
		      System.out.println(content);//如果想把结果输出到页面，直接定义变量就行  
		    }  
		   } catch (IOException e) {  
		    e.printStackTrace();  
		   }  
		  } 
	}


