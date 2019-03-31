package cn.socket.loginMoreClient;

import java.net.Socket;

public class ServerThread extends Thread{
	//和本线程相关的socket
	Socket socket=null;
	String msg="";
	public ServerThread (Socket socket,String msg){
		this.socket=socket;
		this.msg=msg;
	}
	//线程启动
	public void run(){
		//1.建立一个服务器Socket（ServerSocket）绑定指定端口并开始监听
				try {
					 byte[] srtbyte=new byte[2048];
					//4.读取客户端信息请求信息
					 byte[] br =SocketUtil.recData(socket);
					 String ret = new String(br);
		             System.out.println("客户端发送过来的是: " + ret);
		             //if (ret.substring(184, 187).equals("999"))
		             if(ret.trim().equals("0000")){//客户端结束请求
		            	 String statusCode="0000                                                            ";
		            	 srtbyte=statusCode.getBytes();
		            	 //返回64位结束码
		            	 SocketUtil.sendReq(socket,srtbyte);
		            	 System.out.println("服务端将关闭连接");
		            	 SocketUtil.closeConn(socket);
		             }else{
		            	 //获取客户端数据进行入库操作
		            	 //返回第n个数据
		            	 srtbyte = msg.getBytes();
		            	 //向客户端发送数据
		            	 SocketUtil.sendReq(socket,srtbyte);
		             }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	  
	    
	  
	    
	    public static void main(String[] args) {
	    	String baotou ="09       00       11200                                                                                                            0000000000000.00      1900-01-011900-01-01  999                                                                                                                                              ";
	    	System.out.println(baotou.substring(1, 2).equals("22"));
	    }
}
