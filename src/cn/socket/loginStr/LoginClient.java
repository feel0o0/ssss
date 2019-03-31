package cn.socket.loginStr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginClient {
	
	public static void main(String[] args) {
		//1.�����ͻ���socket�����ӣ�ָ����������λ���Լ��˿�
		try {
			Socket socket=new Socket("localhost",8800);
			//2.�õ�socket�Ķ�д��
			OutputStream os=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);
			//�������Ĳ���
			InputStream is=socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			//3.����������һ����Э���socket���ж�/д����
			String info="�û�����tom;�û����룺123456";
			pw.write(info);
			pw.flush();
			socket.shutdownOutput();//���ô��׽��ֵ������
			String reply=null;
			while((reply=br.readLine())!=null){
				System.out.println("���ǿͻ��ˣ����������ҵĻ�Ӧ��"+reply);
			}
			//4.�ر���Դ
			br.close();
			is.close();
			pw.close();
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
