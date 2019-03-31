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
		//1.�����ͻ���socket�����ӣ�ָ����������λ���Լ��˿�----------------
		try {
			Socket socket=new Socket("localhost",8800);
			//2.�õ�socket�Ķ�д��------
			OutputStream os=socket.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(os);
			//�������Ĳ���
			InputStream is=socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			//3.����������һ����Э���socket���ж�/д����
			User user=new User();
			user.setLoginName("Tom");
			user.setPwd("123");
			oos.writeObject(user);
			socket.shutdownOutput();//���ô��׽��ֵ������
			String reply=null;
			while((reply=br.readLine())!=null){
				System.out.println("���ǿͻ��ˣ����������ҵĻ�Ӧ��"+reply);
			}
			//4.�ر���Դ
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
