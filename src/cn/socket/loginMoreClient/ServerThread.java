package cn.socket.loginMoreClient;

import java.net.Socket;

public class ServerThread extends Thread{
	//�ͱ��߳���ص�socket
	Socket socket=null;
	String msg="";
	public ServerThread (Socket socket,String msg){
		this.socket=socket;
		this.msg=msg;
	}
	//�߳�����
	public void run(){
		//1.����һ��������Socket��ServerSocket����ָ���˿ڲ���ʼ����
				try {
					 byte[] srtbyte=new byte[2048];
					//4.��ȡ�ͻ�����Ϣ������Ϣ
					 byte[] br =SocketUtil.recData(socket);
					 String ret = new String(br);
		             System.out.println("�ͻ��˷��͹�������: " + ret);
		             //if (ret.substring(184, 187).equals("999"))
		             if(ret.trim().equals("0000")){//�ͻ��˽�������
		            	 String statusCode="0000                                                            ";
		            	 srtbyte=statusCode.getBytes();
		            	 //����64λ������
		            	 SocketUtil.sendReq(socket,srtbyte);
		            	 System.out.println("����˽��ر�����");
		            	 SocketUtil.closeConn(socket);
		             }else{
		            	 //��ȡ�ͻ������ݽ���������
		            	 //���ص�n������
		            	 srtbyte = msg.getBytes();
		            	 //��ͻ��˷�������
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
