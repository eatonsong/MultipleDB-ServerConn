package org.ssm.shell;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.ssm.model.Server;
import org.ssm.model.ShellMessage;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;




/**
 * 利用JSch包实现远程主机SHELL命令执行
 * @param ip 主机IP
 * @param user 主机登陆用户名
 * @param psw  主机登陆密码
 * @param port 主机ssh2登陆端口，如果取默认值，传-1
 * @param privateKey 密钥文件路径
 * @param passphrase 密钥的密码
 */
public class sshShell{
	private Session session;
	private Channel channel;
	private InputStream instream;
	private OutputStream outstream;
	
	public static void main(String[] args) {
		InitializeServer is=new InitializeServer();
		Server dt=is.getServer("dt");
		try {
			//发送需要执行的SHELL命令，需要用\n结尾，表示回车
			String shellCommand = "cd /home/user1/interface_xml/savePolicy/20170522 \n";
			String shellCommand1 = "ls \n";
			List<String> commands=new ArrayList<String>();
			commands.add(shellCommand);
			commands.add(shellCommand1);
			ShellMessage msg=new ShellMessage();
			msg.setCommands(commands);
			sshShell shell=new sshShell();
			shell.ConnectSell(dt);
			ShellMessage msg1=shell.sshShellExecute(msg);
			shell.disConnectShell();
			List<String> msg2=msg1.getMessages();
			for (String	 message : msg2) {
				System.out.println("返回信息：");
				System.out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取session和channel
	 * @param server
	 */
	public void ConnectSell(Server server){
		JSch jsch = new JSch();
		String privateKey=server.getPrivateKey();
		String passphrase=server.getPassphrase();
		int port=server.getPort();
		String user=server.getUser();
		String ip=server.getIp();
		String psw=server.getPsw();
		try {
			//设置密钥和密码
			if (privateKey != null && !"".equals(privateKey)) {
				if (passphrase != null && "".equals(passphrase)) {
					//设置带口令的密钥
					jsch.addIdentity(privateKey, passphrase);
				} else {
					//设置不带口令的密钥
					jsch.addIdentity(privateKey);
				}
			}
			if(port <=0){
				//连接服务器，采用默认端口
				session = jsch.getSession(user, ip);
			}else{
				//采用指定的端口连接服务器
				session = jsch.getSession(user, ip ,port);
			}
			//如果服务器连接不上，则抛出异常
			if (session == null) {
				throw new Exception("session is null");
			}
			//设置登陆主机的密码
			session.setPassword(psw);//设置密码  
			//设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			//设置登陆超时时间  
			session.connect(30000);
			//创建sftp通信通道
			channel = (Channel) session.openChannel("shell");
			channel.connect(1000);
			//获取输入流和输出流
			instream = channel.getInputStream();
			outstream = channel.getOutputStream();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 与服务器断开连接
	 */
	public void disConnectShell(){
		try {
			if(outstream!=null){
				outstream.close();
			}
			if(instream!=null){
				instream.close();
			}
			if(session!=null){
				session.disconnect();
			}
			if(channel!=null){
				channel.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 执行shell命令
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public ShellMessage sshShellExecute(ShellMessage message) throws Exception{
		try {
			List<String> commands=message.getCommands();
			List<String> messages=new ArrayList<String>();
			for (String command : commands) {
				outstream.write(command.getBytes());
				outstream.flush();
				//获取命令执行的结果
				if (instream.available() > 0) {
					byte[] data = new byte[instream.available()];
					int nLen = instream.read(data);
					if (nLen < 0) {
						throw new Exception("network error.");
					}
					//转换输出结果并打印出来
					String temp = new String(data, 0, nLen,"iso8859-1");
					messages.add(temp);
				}
			}
			message.setMessages(messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	
}