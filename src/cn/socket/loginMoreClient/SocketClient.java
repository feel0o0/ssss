package cn.socket.loginMoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	public static final String IP_ADDR = "192.168.0.36";//ʯ��ׯ���в��Ե�ַ   172.16.13.88  ��·���Ĳ��Ե�ַ10.64.240.133
    public static final int PORT =8800;//�������˿ں�  
    private static OutputStream mOutputStream;
	private static InputStream mInputStream; 
	public static void main(String[] args) {
		//1.�����ͻ���socket�����ӣ�ָ����������λ���Լ��˿�
		try {
			int num=0;
			while(true){
				
				Socket socket=new Socket(IP_ADDR,PORT);
				//����һ�����׽��ֲ��������ӵ�ָ�������ϵ�ָ���˿ں�  
				//��������˷�������    
				String msg="";
				String ret="";
				if(num==0){
					msg="999";
				}else{
					msg="111";
				}
				byte[] srtbyte=msg.getBytes();
				sendReq(socket,srtbyte);
				
				//��ȡ������������    
				byte[] br =recData(socket);
				 ret = new String(br);
				System.out.println("�������˷��ع�����: " + ret+"�����ݰ�"); 
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
	           
	                	//logger.info("��ȡ���������", mInputStream.available()+"");  
	                
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
}
