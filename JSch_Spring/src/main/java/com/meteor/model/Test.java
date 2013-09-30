package com.meteor.model;

import java.io.InputStream;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Test {

	public void test(){
		
		JSch jsch = new JSch();//JSch 객체 선언
		System.out.println("hello");

		
		try {
			Session session = jsch.getSession("test", "192.168.187.134");//세션 생성 (ID , Host)
			session.setConfig("StrictHostKeyChecking" , "no");//Key Checking
			
			session.setPassword("1");//비밀번호 설정
			session.connect(30000);//Connection Timeout
			
			ChannelExec channelExec = (ChannelExec)session.openChannel("exec");//세션에 채널 생성
			channelExec.setPty(true);
			
			channelExec.setCommand("cat text.txt");//Command 날리기
			//channelExec.setCommand("ls");
			//channelExec.setCommand("df -H");
			InputStream inputStream = channelExec.getInputStream();
			
			InputStream err = channelExec.getErrStream();
			channelExec.connect(3000);
			
			String output="";
			//byte[] buf = new byte[1024];
			byte[] buf = new byte[1];
			int length;
			int number = 0;
			while((length = inputStream.read(buf))!=-1){
				number++;
				output += new String(buf,0,length);
				System.out.println( new String(buf,0,length) );
			}
			System.out.println("OUTPUT : ");
			System.out.println(output);
			
			//System.out.println("OUTPUT : " + output);
			System.out.println(number);
			channelExec.disconnect();
		
			session.disconnect();
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			System.out.println(e);
		}
		//jsch.getSession("")
		
	}
	
}
