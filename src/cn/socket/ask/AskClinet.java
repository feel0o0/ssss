package cn.socket.ask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
//
public class AskClinet {
	
	public static void main(String[] args) {
		//1.ȷ�����͸�����������Ϣ����������ַ�Լ��˿�
		String mess="��ã�������ѯ��һ������";
		byte[] buf=mess.getBytes();
		InetAddress ia=null;
		try {
			 ia=InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int port=8800;
		//2.�������ݰ�������ָ�����ȵ���Ϣ��ָ����������ָ���˿�
		DatagramPacket dp=new DatagramPacket(buf,buf.length,ia,port);
		//3.����DatagramSocket����
		DatagramSocket ds=null;
		try {
			 ds=new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//4.��������������ݰ�
		try {
			ds.send(dp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���ܷ���������Ӧ
		byte[] replys=new byte[1024];
		//�����������͵����ݰ������ݽ��洢��������
		DatagramPacket dp2=new DatagramPacket(replys,replys.length);
		//ͨ���׽��ֽ�������
		try {
			ds.receive(dp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��������������Ӧ
		String reply=new String(replys,0,replys.length);
		System.out.println("�������˸��Ļ�Ӧ��"+reply);
		//5.�ͷ���Դ
		ds.close();
	}

}
