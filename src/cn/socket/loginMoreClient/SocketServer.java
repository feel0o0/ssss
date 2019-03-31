package cn.socket.loginMoreClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ��������
 */
public class SocketServer {
	public static void main(String[] args) {
		
		Socket socket=null;
			try {
				//1.����һ��������Socket��ServerSocket����ָ���˿ڲ���ʼ����
				ServerSocket serverSocket=new ServerSocket(8800);
				
				//�ͻ��˵ĵڼ�������
				int sum=0;
				while(true){
					sum++;
		        	if(sum ==3){
		        		break;
		        	}
		        	int index=sum;// ��¼�������ݰ�����
			        int indexNum = 0 ; //���͵Ĵ���
			      //2.ʹ��accept()���������ȴ�����������µ�����
					//�÷�����һֱ���ڼ���״̬
			        while(true){
			        indexNum++;
			        try {
			        	
						socket=serverSocket.accept();
						 StringBuffer buffer = null;
						 String ret="";
						 if(index==1&&indexNum==1){//��һ�ζ�ȡ�ͻ�������
							 //��ȡ��ͷ
							 //��ȡ������������    
				              byte[] br =SocketUtil.recData(socket);
				              ret=new String(br);
				              System.out.println("------"+ret);
				              buffer = new StringBuffer(ret);
				              if( ret.substring(184, 187).equals("999")){
				            	  //����ͷ�������ֶ���Ϊ000
				            	  buffer.replace(184, 187,"000");
				            	  //�����ݷ��͸��ͻ���
				            	  SocketUtil.sendReq(socket, buffer.toString().getBytes());
				              }
							}else{
								
								if( socket.isClosed()){
									break;
								}else{
									
									String msg="00000"+(indexNum-1);
									ServerThread serverThread=new ServerThread(socket,msg);
									serverThread.start();
									System.out.println("�������������ݳɹ����ش���Ϊ:"+(indexNum-1)+"��");
									
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
