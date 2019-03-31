package cn.socket.entity.loginMoreClient;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 服务器端
 */
public class LoginServer {
	public static void main(String[] args) {
		//1.建立一个服务器Socket（ServerSocket）绑定指定端口并开始监听
		
			try {
				ServerSocket serverSocket=new ServerSocket(8800);
				//2.使用accept()方法阻塞等待监听，获得新的连接
				//让服务器一直处于监听状态
				Socket socket=null;
				//访问的客户数量为
				int num=0;
				while(true){
					 socket=serverSocket.accept();
					 ServerThread serverThread=new ServerThread(socket);
					 serverThread.start();
					 num++;
					 System.out.println("客户数量为:"+num);
					 //获得客户的ip信息
					 InetAddress ia=socket.getInetAddress();
					 //客户的ip地址
					 String ip=ia.getHostAddress();
					 System.out.println("本客户的ip地址是"+ip);
					 //客户的主机名称
					 String hostName=ia.getHostName();
					 System.out.println("本客户的主机名称是"+hostName);
					 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
