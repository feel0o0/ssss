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
		//1.确定发送给服务器的信息，服务器地址以及端口
		String mess="你好，我想咨询你一件问题";
		byte[] buf=mess.getBytes();
		InetAddress ia=null;
		try {
			 ia=InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int port=8800;
		//2.创建数据包，发送指定长度的信息到指定服务器的指定端口
		DatagramPacket dp=new DatagramPacket(buf,buf.length,ia,port);
		//3.创建DatagramSocket对象
		DatagramSocket ds=null;
		try {
			 ds=new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//4.向服务器发送数据包
		try {
			ds.send(dp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//接受服务器的响应
		byte[] replys=new byte[1024];
		//创建接收类型的数据包，数据将存储在数组中
		DatagramPacket dp2=new DatagramPacket(replys,replys.length);
		//通过套接字接收数据
		try {
			ds.receive(dp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//解析服务器的响应
		String reply=new String(replys,0,replys.length);
		System.out.println("服务器端给的回应是"+reply);
		//5.释放资源
		ds.close();
	}

}
