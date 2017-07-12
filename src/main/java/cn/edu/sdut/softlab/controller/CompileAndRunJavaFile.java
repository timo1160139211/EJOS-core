/*
 * file_name: Compileee.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年7月10日 
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GaoYisheng 2017年7月10日 TODO
 */
public class CompileAndRunJavaFile extends HttpServlet implements Serializable {

	/**
	 * 2017-07-10
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Process process;// 执行子进程

	/**
	 * 编译并且运行JAVA代码
	 * 
	 * @author Maple
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");// 获取页面参数

		String className = null; // 类名
		String classStr = null;
		BufferedWriter bw = null;
		try {
			classStr = code.substring(code.indexOf("public class"), code.indexOf("{")).toString();// 获取类名字符串
			String[] classStrArray = classStr.split("\\s{1,}");// 按空格分开
			if (classStrArray.length != 3) {
				req.setAttribute("msg", "编译失败：格式不符合规范，请检查类名是否正确(如：public class YouClassName{})");
			} else {
				className = classStrArray[classStrArray.length - 1];
				File sourceFile = new File(className + ".java");// 保存源代码
				if (sourceFile.exists()) {
					sourceFile.delete();
				}
				FileWriter fr = new FileWriter(sourceFile);
				bw = new BufferedWriter(fr);
				bw.write(code);
				bw.close();
				fr.close();

				Runtime runtime = Runtime.getRuntime();
				process = runtime.exec("cmd");
				Thread.sleep(1000);// 防止cmd.exe程序未启动，当然也可以直接使用javac命令
				// 往控制台注入命令
				bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
				bw.write("javac " + className + ".java \n");
				bw.flush();
				long startFreeMemory = runtime.freeMemory();// Java 虚拟机中的空闲内存量
				// 执行时间也是无法知道，因为dos执行java命令，程序无法知道它到底执行到那里了，两个进程，互不了解
				long startCurrentTime = System.currentTimeMillis();// 获取系统当前时间
				bw.write("java " + className + " \n");
				bw.close();
				// 获取控制台输出的结果
				Thread runtimeInput = new Thread(new RuntimeInput());
				runtimeInput.start();

				// 获取内存信息,实际过程中，是无法得到这个程序到底多少内存，内存的分配有操作系统决定，如果
				// 程序需要，系统会动态分配内存，如果有对象没有引用，可能会被垃圾回收器回收，所以是无法得到到底多少内存的
				// 如果你有办法知道，可以留言,现在得到程序运行前后内存使用率，不过输出的是0，因为程序退出，不占内存了
				Thread.sleep(1000);
				long endCurrentTime = System.currentTimeMillis();
				long endFreeMemory = runtime.freeMemory();
				double useMemory = (startFreeMemory - endFreeMemory) / 1024;
				System.out.println("开始时间:" + startCurrentTime);
				System.out.println("结束时间:" + endCurrentTime);
				long useTime = endCurrentTime - startCurrentTime;

				req.setAttribute("msg", "编译成功,使用时间：" + useTime + "毫秒");// 这个使用时间是大概的,不精确

			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "格式不符合规范，请检查类名是否正确(如：public class YouClassName{}).错误信息:" + e.getMessage());
		}

		req.getRequestDispatcher("/test.jsp").forward(req, resp);
	}

	public class RuntimeInput implements Runnable {

		@Override
		public void run() {
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String content = null;
			try {
				while ((content = br.readLine()) != null) {
					System.out.println(content);// 如果想把结果输出到页面，直接定义变量就行
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}