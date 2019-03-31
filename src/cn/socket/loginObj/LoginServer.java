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
 * ��������
 */
public class LoginServer {
	public static void main(String[] args) {
		//1.����һ��������Socket��ServerSocket����ָ���˿ڲ���ʼ����
		try {
			ServerSocket serverSocket=new ServerSocket(8800);
			//2.ʹ��accept()���������ȴ�����������µ�����
			Socket socket=serverSocket.accept();
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
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
