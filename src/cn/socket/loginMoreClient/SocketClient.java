package cn.socket.loginMoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	public static final String IP_ADDR = "192.168.0.36";//石家庄分行测试地址   172.16.13.88  铁路中心测试地址10.64.240.133
    public static final int PORT =8800;//服务器端口号  
    private static OutputStream mOutputStream;
	private static InputStream mInputStream; 
	public static void main(String[] args) {
		//1.建立客户端socket的连接，指定服务器的位置以及端口
		try {
			int num=0;
			while(true){
				
				Socket socket=new Socket(IP_ADDR,PORT);
				//创建一个流套接字并将其连接到指定主机上的指定端口号  
				//向服务器端发送数据    
				String msg="";
				String ret="";
				if(num==0){
					msg="999";
				}else{
					msg="111";
				}
				byte[] srtbyte=msg.getBytes();
				sendReq(socket,srtbyte);
				
				//读取服务器端数据    
				byte[] br =recData(socket);
				 ret = new String(br);
				System.out.println("服务器端返回过来第: " + ret+"个数据包"); 
				num++;
			}
			//socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static void sendReq(Socket socket,byte[] msg){  
	        try {  
	            mOutputStream=socket.getOutputStream();  
	            mOutputStream.write(msg);  
	            mOutputStream.flush();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	          
	          
	    } 
	  public static byte[] recData( Socket socket){  
	        int count = 0;  
	        try {  
	            mInputStream=socket.getInputStream();  
	              
	            byte[] inDatas = null;  
	            while (count == 0) {  
	                count = mInputStream.available();  
	                try {  
	                    //5秒后无响应  
	                    Thread.sleep(5000);  
	                    if(mInputStream.available()==0){  
	                        //关闭连接释放资源  
	                        closeConn(socket);  
	                        break;  
	                    }  
	                } catch (Exception e) {  
	                    // TODO: handle exception  
	                }  
	           
	                	//logger.info("读取服务端数据", mInputStream.available()+"");  
	                
	            }  
	            if(count==0){  
	                return null;  
	            }else{  
	                inDatas = new byte[count];  
	                mInputStream.read(inDatas);  
	                return inDatas;  
	            }  
	              
	              
	        } catch (IOException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        }  
	          
	          
	        return null;  
	          
	          
	    } 
	  /** 
	     *  关闭连接释放资源 
	     * @param socket 
	     */  
	    public static void closeConn(Socket socket){  
	        if(mOutputStream!=null){  
	            try {  
	                mOutputStream.close();  
	                mOutputStream=null;  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	              
	        }  
	          
	        if(mInputStream!=null){  
	            try {  
	                mInputStream.close();  
	                mInputStream=null;  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        }  
	          
	        if(socket!=null){  
	            try {  
	                socket.close();  
	                socket=null;  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	              
	        }  
	          
	    }  
}
