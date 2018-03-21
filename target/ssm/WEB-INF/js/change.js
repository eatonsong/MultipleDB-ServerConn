/**
 * jq css
 */
$("#accordion").accordion();
var availableTags = [ "ActionScript", "AppleScript", "Asp", "BASIC", "C",
		"C++", "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran", "Groovy",
		"Haskell", "Java", "JavaScript", "Lisp", "Perl", "PHP", "Python",
		"Ruby", "Scala", "Scheme" ];
$('[name="button"]').button();
$("[name='controlgroup']").controlgroup();
/**
 * 提示框
 * @param e
 */
function openDialog(e) {
	$("p").remove("#first");
	$("#dialog").append("<p id='first'>" + e + "</p>");
	$("#dialog").dialog({
		autoOpen : false,
		width : 400,
		buttons : [ {
			text : "确定",
			click : function() {
				$(this).dialog("close");
			}
		}, {
			text : "拒绝",
			click : function() {
				$("p").remove("#first");
				$("#dialog").append("<p id='first'>" + "呵呵" + "</p>");
			}
		} ]
	});
	$("#dialog").dialog("open");
}
/**
 * 测试用信息
 */
function testInsert() {
	var a = "8888、8848\n8888\n8888\n8888";
	var b = "0332、0360\n0332、0334、0360\n0360\n0360";
	var c = "001\n002\n003\n004";
	var d = "保险合同发生争议时协商不成的，指定仲裁机构为*****仲裁委员会。\n本保单车辆实际使用人为***，批退保费或赔款可直接支付给实际使用人。\n本合同项下的索赔权益不得转让。\n该车投保时使用性质为非营业。如上牌后行驶证上使用性质为营业或货运，投保人须在上牌后三个工作日内来保险公司批改使用性质并加费。若出险时使用性质已发生改变且未办理批改手续的，保险人不承担保险责任。";
	var e = "997\n998";
	$("#appjigou").val(a);
	$("#appchanpin").val(b);
	$("#appdaima").val(c);
	$("#appneirong").val(d);
	$("#appbaoliu").val(e);
}
/**
 * 删除
 */
function testDelete(){
	var a = "88";
	var b = "delete";
	var c = "delete";
	var d = "delete";
	var e = "997\n998";
	$("#appjigou").val(a);
	$("#appchanpin").val(b);
	$("#appdaima").val(c);
	$("#appneirong").val(d);
	$("#appbaoliu").val(e);
}
/**
 * 清空
 */
function testClear(){
	$("#appjigou").val("");
	$("#appchanpin").val("");
	$("#appdaima").val("");
	$("#appneirong").val("");
	$("#appbaoliu").val("");
}
/**
 * 从后至前检查数组内容如空就删除
 */
function checkArray(arr){
	if(!arr instanceof Array){
		openDialog("数组转换失败！");
		return;
	}
	for (var i = arr.length-1; i >= 0; i--) {
		if(arr[i]==null||arr[i].trim()===""){
			arr.splice(i,1);
		}else{
			return arr;
		}
	}
}



