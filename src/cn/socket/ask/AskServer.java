package cn.socket.ask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class AskServer {
	
	public static void main(String[] args) {
		//1.�������շ������������׽��֣����󶨶˿ں�
		try {
			DatagramSocket ds=new DatagramSocket(8800);
			//2.ȷ�����ݰ����ܵ����ݵ�����Ĵ�С
			byte[] buf=new byte[1024];
			//3.�����������͵����ݰ������ݽ��洢��������
			DatagramPacket dp=new DatagramPacket(buf,buf.length);
			try {
				//4.ͨ���׽��ֽ�������
				ds.receive(dp);
				//5.�������ͷ����͵�����
				String msgg=new String(buf,0,buf.length);
				System.out.println("�ͻ���˵��"+msgg);
				//��Ӧ�ͻ���
				String replty="��ã����ڵģ�����ѯ";
				byte[] repltys=replty.getBytes();
				//��Ӧ��ַ
				SocketAddress sa=dp.getSocketAddress();
				//���ݰ�
				DatagramPacket dp2=new DatagramPacket(repltys,repltys.length,sa);
				//�������ݰ�
				ds.send(dp2);
				//6.�ͷ���Դ
				ds.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
