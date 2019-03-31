package cn.socket.ask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class AskServer {
	
	public static void main(String[] args) {
		//1.创建接收方（服务器）套接字，并绑定端口号
		try {
			DatagramSocket ds=new DatagramSocket(8800);
			//2.确定数据包接受的数据的数组的大小
			byte[] buf=new byte[1024];
			//3.创建接受类型的数据包、数据将存储在数组中
			DatagramPacket dp=new DatagramPacket(buf,buf.length);
			try {
				//4.通过套接字接受数据
				ds.receive(dp);
				//5.解析发送方发送的数据
				String msgg=new String(buf,0,buf.length);
				System.out.println("客户端说："+msgg);
				//响应客户端
				String replty="你好，我在的，请咨询";
				byte[] repltys=replty.getBytes();
				//响应地址
				SocketAddress sa=dp.getSocketAddress();
				//数据包
				DatagramPacket dp2=new DatagramPacket(repltys,repltys.length,sa);
				//发送数据包
				ds.send(dp2);
				//6.释放资源
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
