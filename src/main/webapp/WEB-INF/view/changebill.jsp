<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Test</title>
<link
	href="${pageContext.request.contextPath }/jquery-ui-1.12.1.custom/jquery-ui.css"
	rel="stylesheet">
<style>
body {
	font-family: "Trebuchet MS", sans-serif;
	margin: 50px;
}

.demoHeaders {
	margin-top: 2em;
}

#dialog-link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog-link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

#icons {
	margin: 0;
	padding: 0;
}

#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}

.fakewindowcontain .ui-widget-overlay {
	position: absolute;
}

select {
	width: 100px;
}

textarea {
	height: 200px;
}
</style>
</head>
<body>
	<input type="hidden" id="path"
		value="${pageContext.request.contextPath }/bill/" />
	<form action="${pageContext.request.contextPath }/bill/changeBill"
		name="billtable" method="post">
		<h2 class="demoHeaders">ChangeBillTest</h2>
		<fieldset>
			<legend>
				<strong>修改金额</strong>
			</legend>
			<div name="controlgroup">
				<label>投保单号（联合只需输入一单）</label> <input type="text" name="appNo"
					style="width: 200px; height: 28px" />&nbsp;
			</div>
			<label>数据库：</label>
			<div name="controlgroup">
				<select id="environment" name="environment" style="width: 100px">
					<option selected="selected" value="IT">it_db</option>
					<option value="DT">dt_db</option>
					<option value="ST">st_db</option>
					<option value="UAT">uat_db</option>
					<option value="ALL">全给朕改一遍</option>
				</select>&nbsp;
			</div>
			<div name="controlgroup">
				<label>金额:</label> <input type="text" name="money"
					style="width: 200px; height: 28px" />&nbsp;
			</div>
			<div name="controlgroup">
				<input type="button" name="button" value="提交信息" id="updateBill" />&nbsp;
			</div>
		</fieldset>
	</form>
	<h2 class="demoHeaders">maintainAppoint</h2>
	<fieldset style="display: none;">
		<legend>
			<strong>维护特约</strong>
		</legend>
		<table>
			<tr>
				<td>适用机构：</td>
				<td>适用产品：</td>
				<td>特约代码：</td>
				<td>特约内容：</td>
				<td>保留特约代码：</td>
				<td rowspan="2">
					<table>
						<tr>
							<td colspan="2">
								<div name="controlgroup">
									<table>
										<tr>
											<td rowspan="2">数据库：</td>
											<td><label for="db_it">db_it</label> <input
												type="checkbox" name="db" id="db_it" value="IT">&nbsp;</td>
											<td><label for="db_dt">db_dt</label> <input
												type="checkbox" name="db" id="db_dt" value="DT">&nbsp;<br />
												</td>
										</tr>
										<tr>
											<td><label for="db_st">db_st</label> <input
												type="checkbox" name="db" id="db_st" value="ST">&nbsp;</td>
											<td><label for="db_uat">db_uat</label> <input
												type="checkbox" name="db" id="db_uat" value="UAT">&nbsp;</td>
										</tr>
									</table>
									<hr>
								</div>
							</td>
						</tr>
						<tr>
							<td> <input type="button" id="test" name="button"
								value="insert" />&nbsp;
								<input type="button" id="delAppoint" name="button"
								value="delete" />&nbsp;
								<input type="button" id="updateAppoint" name="button"
								value="submit" />&nbsp;
								<input type="button" id="clear" name="button"
								value="clear" />&nbsp;
							</td>
						</tr>
						<tr>
							<td>
							<span style="color: red">*</span>以换行为下一条内容,每次只能更新一个分公司特约<br>
							<span style="color: red">*</span>插入特约为先删除再插入,未保留特约代码将被删除<br>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><textarea id="appjigou"></textarea></td>
				<td><textarea id="appchanpin"></textarea></td>
				<td><textarea id="appdaima"></textarea></td>
				<td><textarea id="appneirong"></textarea></td>
				<td><textarea id="appbaoliu"></textarea></td>
			</tr>
		</table>
	</fieldset>
		<div id="dialog" title="温馨提示"></div>
		<script	src="${pageContext.request.contextPath }/jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
		<script	src="${pageContext.request.contextPath }/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
		<script	src="${pageContext.request.contextPath }/js/change.js"></script>
		<script type="text/javascript">
			$(function() {
				var path = $("#path").val();
				$("#test").bind("click", function() {
					testInsert();
				});
				$("#delAppoint").bind("click", function() {
					testDelete();
				});
				$("#clear").bind("click", function() {
					testClear();
				});
				$("#updateAppoint").bind("click",function() {
					var appjigou = checkArray($("#appjigou").val().split("\n"));
					var appchanpin = checkArray($("#appchanpin").val().split("\n"));
					var appdaima = checkArray($("#appdaima").val().split("\n"));
					var appneirong = checkArray($("#appneirong").val().split("\n"));
					for (var i = 0; i < appneirong.length; i++) {//替换英文,
						var str = appneirong[i].replace(/,/g,'，');
						str=appneirong[i].replace(/\%/g,'%25');
						if(str!=appneirong[i]){
							appneirong.splice(i,1,str);
						}
					}
					var appbaoliu = checkArray($("#appbaoliu").val().split("\n"));
					var db=new Array();
					if(!($("#appjigou").val()&&$("#appchanpin").val()&&$("#appdaima").val()&&$("#appneirong").val())){
						openDialog("请粘贴内容！");
						return;
					}
					if (!(appjigou.length === appchanpin.length && appdaima.length ===appneirong.length&&appjigou.length === appneirong.length)) {
						openDialog("请检查输入内容条数是否一致！");
						return;
					}
					$("input:checkbox[name='db']:checked").each(function(){
							db.push($(this).val());
					} );
					if(db.length===0){
						openDialog("请选库！");
						return;
					}
					var postdata = "appjigou=" + appjigou
											+ "&appchanpin=" + appchanpin
											+ "&appdaima=" + appdaima
											+ "&appneirong=" + appneirong
											+ "&appbaoliu=" + appbaoliu
											+"&environments="+db;
					$.post(path + "changeAppoint",postdata,function(data) {
						openDialog(data);
					});
				});
				$('[name="money"]').val(0.01);
				var flag = '${flag}';
				if (flag) {
					if (flag !=null) {
						openDialog(flag);
					} 
				}
				var bij = '${bi}';
				if (bij) {
					var bi = eval("(" + bij + ")");
					$('[name="appNo"]').val(bi.appNo);
					$('[name="money"]').val(bi.money);
					$('[value=' + bi.environment + ']').attr("selected", true);
				}
				$('#updateBill').click(function() {
					var appNo = $('[name="appNo"]');
					var money = $('[name="money"]');
					if (appNo.val() == null || appNo.val() == "") {
						openDialog("投保单号不能为空！");
						appNo.focus();
						return;
					} else if (money.val() == null || money.val() == "") {
						openDialog("金额不能为空！");
						money.focus();
						return;
					} else if (appNo.val().length != 22) {
						openDialog("投保单号格式不正确检查是否有空格，瓜娃子！");
						appNo.focus();
						return;
					} else {
						$('[name="billtable"]').submit();
					}
				});
			});
		</script>
</body>
</html>
