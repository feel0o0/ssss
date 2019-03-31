package cn.socket.loginMoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketUtil {
	private static InputStream mInputStream; 
	private static OutputStream mOutputStream; 
	 /**
     * ��ȡ�ͻ�������
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
                    Thread.sleep(2000);  
                    if(mInputStream.available()==0){  
                        //�ر������ͷ���Դ  
                        closeConn(socket);  
                        break;  
                    }  
                } catch (Exception e) {  
                    // TODO: handle exception  
                }  
                	//System.err.printf("��ȡ�ͻ�������", mInputStream.available()+"");
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
    /**
	   * ��ͻ��˷�������
	   * @param socket
	   * @param msg
	   */
	    public static void sendReq(Socket socket,byte[] msg){  
	   
			
	        try {  
	            mOutputStream=socket.getOutputStream(); 
	            mOutputStream.write(msg);
	            mOutputStream.flush();
	           /* PrintWriter pw=new PrintWriter(mOutputStream);
	            pw.write(new String(msg));  
	            pw.flush();  */
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	          
	          
	    } 
}
