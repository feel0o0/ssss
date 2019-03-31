package cn.socket.entity.loginMoreClient;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ��������
 */
public class LoginServer {
	public static void main(String[] args) {
		//1.����һ��������Socket��ServerSocket����ָ���˿ڲ���ʼ����
		
			try {
				ServerSocket serverSocket=new ServerSocket(8800);
				//2.ʹ��accept()���������ȴ�����������µ�����
				//�÷�����һֱ���ڼ���״̬
				Socket socket=null;
				//���ʵĿͻ�����Ϊ
				int num=0;
				while(true){
					 socket=serverSocket.accept();
					 ServerThread serverThread=new ServerThread(socket);
					 serverThread.start();
					 num++;
					 System.out.println("�ͻ�����Ϊ:"+num);
					 //��ÿͻ���ip��Ϣ
					 InetAddress ia=socket.getInetAddress();
					 //�ͻ���ip��ַ
					 String ip=ia.getHostAddress();
					 System.out.println("���ͻ���ip��ַ��"+ip);
					 //�ͻ�����������
					 String hostName=ia.getHostName();
					 System.out.println("���ͻ�������������"+hostName);
					 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
