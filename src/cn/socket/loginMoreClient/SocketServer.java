package cn.socket.loginMoreClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 服务器端
 */
public class SocketServer {
	public static void main(String[] args) {
		
		Socket socket=null;
			try {
				//1.建立一个服务器Socket（ServerSocket）绑定指定端口并开始监听
				ServerSocket serverSocket=new ServerSocket(8800);
				
				//客户端的第几次请求
				int sum=0;
				while(true){
					sum++;
		        	if(sum ==3){
		        		break;
		        	}
		        	int index=sum;// 记录接受数据包次数
			        int indexNum = 0 ; //发送的次数
			      //2.使用accept()方法阻塞等待监听，获得新的连接
					//让服务器一直处于监听状态
			        while(true){
			        indexNum++;
			        try {
			        	
						socket=serverSocket.accept();
						 StringBuffer buffer = null;
						 String ret="";
						 if(index==1&&indexNum==1){//第一次读取客户端数据
							 //读取包头
							 //读取服务器端数据    
				              byte[] br =SocketUtil.recData(socket);
				              ret=new String(br);
				              System.out.println("------"+ret);
				              buffer = new StringBuffer(ret);
				              if( ret.substring(184, 187).equals("999")){
				            	  //将包头返回码字段置为000
				            	  buffer.replace(184, 187,"000");
				            	  //将数据发送给客户端
				            	  SocketUtil.sendReq(socket, buffer.toString().getBytes());
				              }
							}else{
								
								if( socket.isClosed()){
									break;
								}else{
									
									String msg="00000"+(indexNum-1);
									ServerThread serverThread=new ServerThread(socket,msg);
									serverThread.start();
									System.out.println("服务器接受数据成功返回次数为:"+(indexNum-1)+"次");
									
								}
							}
						 
						 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        } 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				SocketUtil.closeConn(socket);
			}
			
	}
	
	
}
