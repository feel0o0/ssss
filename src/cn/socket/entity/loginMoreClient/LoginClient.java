package cn.socket.entity.loginMoreClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.socket.entity.User;

public class LoginClient {
	
	public static void main(String[] args) {
		//1.建立客户端socket的连接，指定服务器的位置以及端口----------------
		try {
			Socket socket=new Socket("localhost",8800);
			//2.得到socket的读写流------
			OutputStream os=socket.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(os);
			//输入流的操作
			InputStream is=socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			//3.利用流按照一定的协议对socket进行读/写操作
			User user=new User();
			user.setLoginName("Tom");
			user.setPwd("123");
			oos.writeObject(user);
			socket.shutdownOutput();//禁用此套接字的输出流
			String reply=null;
			while((reply=br.readLine())!=null){
				System.out.println("我是客户端，服务器给我的回应是"+reply);
			}
			//4.关闭资源
			br.close();
			is.close();
			oos.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
