package cn.socket.loginMoreClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class socketServer1 {
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory.getLogger(socketServer1.class);
	public static final String IP_ADDR = "192.168.0.189";//石家庄分行测试地址   172.16.13.88  铁路中心测试地址10.64.240.133
    public static final int PORT =8800;//服务器端口号  
    private static OutputStream mOutputStream;
	private static InputStream mInputStream; 
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
	 
	 /**
		 * 开启socket服务传送数据
		 * @param String baotou , int flag  1/  拼接的报头  2、标记查询那张表进行发送
		 * */
	    public static void SocketClient(List<File> files,int flag) {    
	    	
	    	logger.info("客户端启动..."); 
	    		
		    	String fl =String.valueOf(5);
		    	String baotou = ""; //包头
		    	if(flag == 1){
		    		String name ="34";
		    		if("34".equals(name)){
			    		baotou ="34       00       11200                                                                                                            0000000000000.00      1900-01-011900-01-01"+fl+"999                                                                                                                                              ";
		    		}else{
			    		baotou ="09       00       11200                                                                                                            0000000000000.00      1900-01-011900-01-01"+fl+"999                                                                                                                                              ";
		    		}
		    		
		    	}else{
		    		baotou ="34       00       11300                                                                                                            0000000000000.00      1900-01-011900-01-01"+fl+"999                                                                                                                                              ";
		    	}
		//    	logger.info("当接收到服务器端字符为 \"0000\" 的时候, 客户端将终止\n");   
		    	System.out.println("发送过去的包头是："+baotou);
		    	int sum = 0;
		        while (true) {    
		        	sum++;
		        	if(sum ==3){
		        		break;
		        	}
		            Socket socket = null;  
		            int index=sum;// 记录发送数据包次数
		            int indexNum = 0 ; //发送的次数
		            //String filePath = "";
		            try {  
		                //创建一个流套接字并将其连接到指定主机上的指定端口号  
		                socket = new Socket(IP_ADDR, PORT);    
		                //向服务器端发送数据    
		                if(index==1){
		//                	 PrintWriter  out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true) ;  
		                    byte[] srtbyte = baotou.getBytes();
		                	sendReq(socket,srtbyte);
		                }
		        		 //读取服务器端数据    
		                byte[] br =recData(socket);
		                String ret = new String(br);
		                logger.info("服务器端返回过来的是: " + ret);    
		                if (ret.substring(184, 187).equals("000")) {//通信成功响应报头 ，开始准备数据准备发送
		                	 byte[] buffer=new byte[2048];
		                     int count=0;
		                      try{
	                              InputStream inputStream1=new FileInputStream(ret);
	                              while(-1!=(count=inputStream1.read(buffer))){
	                            	  indexNum++;
	                            	  String str =new  String(buffer,0,count);
	                            	  byte[] srtbyte = str.getBytes();
	                            	  sendReq(socket,srtbyte);
	               	        		
	                            	  byte[] br1 =recData(socket);
	                                  String ret1 = new String(br1);
	       	                		  logger.info("服务器端返回过来的是: " + ret1); 
	               	                  String value = String.valueOf("00000"+indexNum);
	               	                  if(ret1.equals(value)){
		               	            	 logger.info("服务器接受数据成功返回次数为"+index);
	               	                  }else{
		               	            	 logger.info("返回代码异常");  
		               	            	 break;  
	               	                  }
		                            }
		                              //文件发送完成结束发送0000
		                              String str = "0000";
		                              byte[] cloStr = str.getBytes();
		                              sendReq(socket,cloStr);
		             	        		
		                              byte[] br1 =recData(socket);
		                              String str2 = new String(br1);
		             	               if ((str2.trim()).equals("0000")){//返回64位状态
		             	            	 
		             	            		  logger.info("debit表已跟新状态success");
		             	            	   
		             	            		  logger.info("Delay表已跟新状态success");
		             	            	   
		             	            		logger.info("客户端将关闭连接");  
		             	            		closeConn(socket);
		             	               }
		                      }catch(IOException ex){
		                              ex.printStackTrace();
		                      }
		                      break;
						}
		            } catch (Exception e) {  
		            	
		            	logger.error("客户端异常:" + e.getMessage());   
		            } finally {  
		            	closeConn(socket);
		            	logger.info("客户端已关闭连接");  
		            } 
		        }    
	    	}
	    
	    
	    /** 
	     * 从服务端返回的数据 
	     * @param socket 
	     * @return 
	     */  
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
	           
	                	logger.info("读取服务端数据", mInputStream.available()+"");  
	                
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
	    public static void main(String[] args) {
	    	List<File> files = null;
	    	int flag = 0;
	    	SocketClient( files, flag);
		}
}
