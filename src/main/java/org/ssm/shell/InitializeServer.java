package org.ssm.shell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.ssm.model.Server;

public class InitializeServer {
	private static Server itServer;
	private static Server dtServer;
	private static Server stServer;
	private static Server uatServer;
	static{
		  try {//requestUrl=pro.getProperty("VehicleQuery_Url");
			  Properties prop = new Properties();     
			  //读取属性文件a.properties
			  InputStream in = InitializeServer.class.getResourceAsStream("/linuxServer.properties");
			  prop.load(in);     ///加载属性列表
			  String ituser=prop.getProperty("it.user");
			  String itip=prop.getProperty("it.ip");
			  String itpsw=prop.getProperty("it.psw");
			  int itport=Integer.parseInt(prop.getProperty("it.port"));
			  String dtuser=prop.getProperty("dt.user");
			  String dtip=prop.getProperty("dt.ip");
			  String dtpsw=prop.getProperty("dt.psw");
			  int dtport=Integer.parseInt(prop.getProperty("dt.port"));
			  String stuser=prop.getProperty("st.user");
			  String stip=prop.getProperty("st.ip");
			  String stpsw=prop.getProperty("st.psw");
			  int stport=Integer.parseInt(prop.getProperty("st.port"));
			  String uatuser=prop.getProperty("uat.user");
			  String uatip=prop.getProperty("uat.ip");
			  String uatpsw=prop.getProperty("uat.psw");
			  int uatport=Integer.parseInt(prop.getProperty("uat.port"));
			  itServer=new Server("it", itip, ituser, itpsw, itport);
			  dtServer=new Server("dt", dtip, dtuser, dtpsw, dtport);
			  stServer=new Server("st", stip, stuser, stpsw, stport);
			  uatServer=new Server("uat", uatip, uatuser, uatpsw, uatport);
			  in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Server getServer(String name){
		Server se=null;
		if("it".equals(name)){
			se=itServer;
		}
		if("st".equals(name)){
			se=stServer;
		}
		if("dt".equals(name)){
			se=dtServer;
		}
		if("uat".equals(name)){
			se=uatServer;
		}
		return se;
	}
	
}
