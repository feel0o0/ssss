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
	 * ��־��¼��
	 */
	private static final Logger logger = LoggerFactory.getLogger(socketServer1.class);
	public static final String IP_ADDR = "192.168.0.189";//ʯ��ׯ���в��Ե�ַ   172.16.13.88  ��·���Ĳ��Ե�ַ10.64.240.133
    public static final int PORT =8800;//�������˿ں�  
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
		 * ����socket����������
		 * @param String baotou , int flag  1/  ƴ�ӵı�ͷ  2����ǲ�ѯ���ű���з���
		 * */
	    public static void SocketClient(List<File> files,int flag) {    
	    	
	    	logger.info("�ͻ�������..."); 
	    		
		    	String fl =String.valueOf(5);
		    	String baotou = ""; //��ͷ
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
		//    	logger.info("�����յ����������ַ�Ϊ \"0000\" ��ʱ��, �ͻ��˽���ֹ\n");   
		    	System.out.println("���͹�ȥ�İ�ͷ�ǣ�"+baotou);
		    	int sum = 0;
		        while (true) {    
		        	sum++;
		        	if(sum ==3){
		        		break;
		        	}
		            Socket socket = null;  
		            int index=sum;// ��¼�������ݰ�����
		            int indexNum = 0 ; //���͵Ĵ���
		            //String filePath = "";
		            try {  
		                //����һ�����׽��ֲ��������ӵ�ָ�������ϵ�ָ���˿ں�  
		                socket = new Socket(IP_ADDR, PORT);    
		                //��������˷�������    
		                if(index==1){
		//                	 PrintWriter  out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true) ;  
		                    byte[] srtbyte = baotou.getBytes();
		                	sendReq(socket,srtbyte);
		                }
		        		 //��ȡ������������    
		                byte[] br =recData(socket);
		                String ret = new String(br);
		                logger.info("�������˷��ع�������: " + ret);    
		                if (ret.substring(184, 187).equals("000")) {//ͨ�ųɹ���Ӧ��ͷ ����ʼ׼������׼������
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
	       	                		  logger.info("�������˷��ع�������: " + ret1); 
	               	                  String value = String.valueOf("00000"+indexNum);
	               	                  if(ret1.equals(value)){
		               	            	 logger.info("�������������ݳɹ����ش���Ϊ"+index);
	               	                  }else{
		               	            	 logger.info("���ش����쳣");  
		               	            	 break;  
	               	                  }
		                            }
		                              //�ļ�������ɽ�������0000
		                              String str = "0000";
		                              byte[] cloStr = str.getBytes();
		                              sendReq(socket,cloStr);
		             	        		
		                              byte[] br1 =recData(socket);
		                              String str2 = new String(br1);
		             	               if ((str2.trim()).equals("0000")){//����64λ״̬
		             	            	 
		             	            		  logger.info("debit���Ѹ���״̬success");
		             	            	   
		             	            		  logger.info("Delay���Ѹ���״̬success");
		             	            	   
		             	            		logger.info("�ͻ��˽��ر�����");  
		             	            		closeConn(socket);
		             	               }
		                      }catch(IOException ex){
		                              ex.printStackTrace();
		                      }
		                      break;
						}
		            } catch (Exception e) {  
		            	
		            	logger.error("�ͻ����쳣:" + e.getMessage());   
		            } finally {  
		            	closeConn(socket);
		            	logger.info("�ͻ����ѹر�����");  
		            } 
		        }    
	    	}
	    
	    
	    /** 
	     * �ӷ���˷��ص����� 
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
	                    //5�������Ӧ  
	                    Thread.sleep(5000);  
	                    if(mInputStream.available()==0){  
	                        //�ر������ͷ���Դ  
	                        closeConn(socket);  
	                        break;  
	                    }  
	                } catch (Exception e) {  
	                    // TODO: handle exception  
	                }  
	           
	                	logger.info("��ȡ���������", mInputStream.available()+"");  
	                
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
	     *  �ر������ͷ���Դ 
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
