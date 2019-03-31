package cn.socket.loginObj;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cn.socket.entity.User;

/*
 * 服务器端
 */
public class LoginServer {
	public static void main(String[] args) {
		//1.建立一个服务器Socket（ServerSocket）绑定指定端口并开始监听
		try {
			ServerSocket serverSocket=new ServerSocket(8800);
			//2.使用accept()方法阻塞等待监听，获得新的连接
			Socket socket=serverSocket.accept();
			//3.获得输入流
			InputStream is=socket.getInputStream();
			//获得的流可以把对象反序列化
			ObjectInputStream ois=new ObjectInputStream(is);
			//获得输出流
			OutputStream os=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);
			//4.读取用户输入信息
		
			User user=null;
			try {
				user = (User) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("用户信息为："+user.getLoginName()+"------------"+user.getPwd());
		
			String reply="welcome to China!";
			pw.write(reply);
			pw.flush();
			
			//5.关闭资源
			pw.close();
			os.close();
			ois.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
