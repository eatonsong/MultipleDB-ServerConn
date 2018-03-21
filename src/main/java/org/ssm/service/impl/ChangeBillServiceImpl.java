package org.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssm.mapper.ChangeBillMapper;
import org.ssm.model.AppointDto;
import org.ssm.model.Bill;
import org.ssm.service.ChangeBillService;
import org.ssm.util.DataSourceContextHolder;
//可以消除xml中对bean的配置  
@Service  
//此处使用spring的声明式事务，不在使用sqlsession和提交事务了  
@Transactional  
public class ChangeBillServiceImpl implements ChangeBillService {
	@Resource  
	private ChangeBillMapper billmapper;
	@Override
	public List testfind() {
		// TODO Auto-generated method stub
		DataSourceContextHolder.setDataSourceType("ST");
		return billmapper.testfind();
	}
	/**
	 * 修改金额
	 */
	@Override
	public String changeBill(Bill bi) {
		if(bi.getEnvironment().equals("ALL")){
			StringBuffer result=new StringBuffer();
			result.append(updateAllBill(bi,"IT"));
			result.append(updateAllBill(bi,"ST"));
			result.append(updateAllBill(bi,"DT"));
			result.append(updateAllBill(bi,"UAT"));
			return result.toString();
		}else{
			return updateAllBill(bi,bi.getEnvironment());
		}
	}
	public String updateAllBill(Bill bi,String environment){
		DataSourceContextHolder.setDataSourceType(environment);
		int i=billmapper.changeBill(bi);
		StringBuffer result=new StringBuffer();
		if(i>0){
			result.append("数据库："+environment+",更新成功！");
		}else{
			result.append("数据库："+environment+",更新失败！");
		}
		return result.toString();
	}
	/**
	 * 插入特约
	 */
	@Override
	public String changeApp(AppointDto app) {
		String flag="";
		for(String environment:app.getEnvironments()){
			String code=this.updateAppStatus(app, environment);
			flag+=code;
		}
		return flag;
	}
	public String updateAppStatus(AppointDto app,String environment){
		DataSourceContextHolder.setDataSourceType(environment);
		int i=billmapper.deleteApp(app);
		int j=billmapper.insertApp(app);
		StringBuffer result=new StringBuffer();
		result.append("环境："+environment);
		result.append("删除行数 "+i+";");
		result.append("插入行数 "+j+";");
		return result.toString();
	}
	/**
	 * 删除特约
	 */
	@Override
	public String deleteApp(AppointDto app) {
		String flag="";
		for(String environment:app.getEnvironments()){
			String code=this.deleteAppStatus(app, environment);
			flag+=code;
		}
		return flag;
	}
	public String deleteAppStatus(AppointDto app,String environment){
		DataSourceContextHolder.setDataSourceType(environment);
		int i=billmapper.deleteApp(app);
		StringBuffer result=new StringBuffer();
		result.append("环境："+environment);
		result.append("删除行数 "+i+";");
		return result.toString();
	}
	
	
}
