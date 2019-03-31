package cn.socket.entity.loginMoreClient;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cn.socket.entity.User;

public class ServerThread extends Thread{
		
	//�ͱ��߳���ص�socket
	Socket socket=null;
	
	public ServerThread (Socket socket){
		this.socket=socket;
	}
	
	//�߳�����
	public void run(){
		//1.����һ��������Socket��ServerSocket����ָ���˿ڲ���ʼ����
				try {
					
					//3.���������
					InputStream is=socket.getInputStream();
					//��õ������԰Ѷ������л�
					ObjectInputStream ois=new ObjectInputStream(is);
					//��������
					OutputStream os=socket.getOutputStream();
					PrintWriter pw=new PrintWriter(os);
					//4.��ȡ�û�������Ϣ
				
					User user=null;
					try {
						user = (User) ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("�û���ϢΪ��"+user.getLoginName()+"------------"+user.getPwd());
				
					String reply="welcome to China!";
					pw.write(reply);
					pw.flush();
					
					//5.�ر���Դ
					pw.close();
					os.close();
					ois.close();
					is.close();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
