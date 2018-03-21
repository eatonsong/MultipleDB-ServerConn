package org.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.model.Appoint;
import org.ssm.model.AppointDto;
import org.ssm.model.Bill;
import org.ssm.service.ChangeBillService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/bill")
public class ChangeBillController {
	@Autowired
	private ChangeBillService changeBillService;

	/**
	 * 测试连接
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getAllUser(Map<String, Object> map) {
		@SuppressWarnings("unchecked")
		List<String> billlist = changeBillService.testfind();
		map.put("billlist", billlist);
		return "bill";
	}
	@RequestMapping(value = "/changeBill", method = RequestMethod.POST)
	public String changeBill(Bill bi,ModelMap map) {
		if(bi!=null){
			String flag = changeBillService.changeBill(bi);
			map.put("flag", flag);
			JSONObject jsonBi = JSONObject.fromObject(bi);
			map.put("bi", jsonBi);
		}
		return "changebill";
	}

	@RequestMapping(value = "/goChangeBill", method = RequestMethod.GET)
	public String goChangeBill() {
		return "changebill";
	}
	@ResponseBody
	@RequestMapping(value = "/changeAppoint", method = RequestMethod.POST)
	public void changeAppoint(HttpServletResponse response, HttpServletRequest request, String appjigou,
			String appchanpin, String appdaima, String appneirong, String appbaoliu, String environments) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");// 这里不设置编码会有乱码
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter(); // 输出中文，这一句一定要放到response.setContentType("text/html;charset=utf-8"),
													// response.setHeader("Cache-Control",
													// "no-cache")后面，否则中文返回到页面是乱码

			if (appjigou != null && !"".equals(appjigou.trim())) {
				String appjigouArray[] = appjigou.trim().split(",");
				String appchanpinArray[] = appchanpin.trim().split(",");
				String appdaimaArray[] = appdaima.trim().split(",");
				String appneirongArray[] = appneirong.split(",");// 内容暂不去空格
				String appbaoliuArray[] = appbaoliu.trim().split(",");
				String environmentsArray[] = environments.split(",");
				String areacode = null;
				for (int i = 1; i < appjigouArray.length; i++) {
					String jigous[] = appjigouArray[0].split("、");
					String jigous1[] = appjigouArray[i].split("、");
					for (String string : jigous) {
						for (String stringi : jigous1) {
							if (!string.substring(0, 2).equals(stringi.substring(0, 2))) {
								out.print("暂不支持多地区同时更新");
								out.flush();
								out.close();
								return;
							}
						}
					}
				}
				areacode = appjigouArray[0].substring(0, 2);
				AppointDto appDto = new AppointDto();
				appDto.setAreaCode(areacode);// 地区代码
				List<String> enList = new ArrayList<String>();
				for (String environment : environmentsArray) {
					if (environment != null) {
						enList.add(environment);
					}
				}
				appDto.setEnvironments(enList);// 环境
				List<String> baoliuList = null;
				if(appbaoliuArray.length>0){
					baoliuList = new ArrayList<>();
					for (String string : appbaoliuArray) {
						baoliuList.add(string);// 保留代码
					}
					appDto.setAppbaoliuList(baoliuList);
				}
				if("delete".equals(appdaima)&&"delete".equals(appneirong)&&"delete".equals(appchanpin)){//删除机构下
					String code=changeBillService.deleteApp(appDto);
					out.print("success返回结果："+code);
					out.flush();
					out.close();
					return;
				}
				
				/**
				 * 组织数据
				 */
				List<Appoint> appointList = new ArrayList<>();
				for (int i = 0; i < appjigouArray.length; i++) {
					String jigous[] = appjigouArray[i].split("、");
					String chanpins[] = appchanpinArray[i].split("、");
					String daima = appdaimaArray[i];
					String neirong = appneirongArray[i];
					for (int j = 0; j < jigous.length; j++) {
						for (int k = 0; k < chanpins.length; k++) {
							if (!("0332".equals(chanpins[k]) || "0360".equals(chanpins[k]))) {
								continue;
							}
							Appoint app = new Appoint();
							app.setAppdaima(daima);
							app.setAppneirong(neirong);
							app.setAppjigou(jigous[j]);
							app.setAppchanpin(chanpins[k]);
							appointList.add(app);
							System.out.println(app.getAppjigou() + "||" + app.getAppdaima() + "||" + app.getAppchanpin()
									+ "||" + app.getAppneirong());
						}
					}
				}
				appDto.setAppointList(appointList);
				String code=changeBillService.changeApp(appDto);
				out.print("success返回结果："+code);
				out.flush();
				out.close();
				return;
			} else {
				out.print("传入参数为空！");
				out.flush();
				out.close();
				return;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
