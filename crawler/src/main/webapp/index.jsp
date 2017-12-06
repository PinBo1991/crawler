<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>携程爬虫示例</title>
	<style type="text/css">
		#xiecheng_web_url{
			width:400px;
			height:18px;
		}
		#JD_web_url{
			width:400px;
			height:18px;
		}
		table,table tr th, table tr td { border:1px solid #0094ff; }
		table {text-align: center; border-collapse: collapse;}   
	</style>
</head>
<body>
	<form id="xiechengForm" name="xiechengForm" method="post" action="xiecheng">
		网址:<input type="text" name="xiecheng_web_url" id="xiecheng_web_url" value="http://hotels.ctrip.com/hotel/nanjing12#ctm_ref=hod_hp_sb_lst">
		编码集:<input type="text" name="xiecheng_web_encode" id="xiecheng_web_encode" value="UTF-8">
		<input type="button" name="xiecheng_saveBtn" id="xiecheng_saveBtn" value="查询携程酒店"> 
	</form>
	<br>
	<br>
	<form id="JDForm" name="JDForm" method="post" action="JD">
		网址:<input type="text" name="JD_web_url" id="JD_web_url" value="https://search.jd.com/Search?keyword=%E7%94%B5%E8%84%91&enc=utf-8&wq=%E7%94%B5%E8%84%91&pvid=4427fa56953c4069ab2199a1da56c21f">
		编码集:<input type="text" name="JD_web_encode" id="JD_web_encode" value="UTF-8">
		<input type="button" name="JD_saveBtn" id="JD_saveBtn" value="查询京东商品"> 
	</form>
	<table id="infoTable">
		<thead>
			<tr>
				<td>图片</td>
				<td>名称</td>
				<td>描述</td>
				<td>价格</td>
			</tr>
		</thead>
		<tbody id="infoTBody">
		</tbody>
	</table>
	<script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="resources/form/jquery.form.js"></script>
	
	<script type="text/javascript">
		$("#xiecheng_saveBtn").click(function(){
			//提交表单
			$("#xiechengForm").submit();
		});
		$("#xiechengForm").ajaxForm({
			success:function(jsonObject){
				var jsonObject = eval(jsonObject);
				var htmlString = "";
				//遍历数组中的元素
				$.each(jsonObject,function(i,n){
					htmlString +="<tr>";
					htmlString +="<td><img src='"+n.hotel_img_src+"'></td>";
					htmlString +="<td>"+n.hotel_title+"</td>";
					htmlString +="<td>"+n.hotel_content+"</td>";
					htmlString +="<td>"+n.hotel_price+"</td>";
					htmlString +="</tr>";
				});
				//将拼接好的字符串追加到tbody中
				$("#infoTBody").append(htmlString);
			}
		});
		
		$("#JD_saveBtn").click(function(){
			//提交表单
			$("#JDForm").submit();
		});
		$("#JDForm").ajaxForm({
			success:function(jsonObject){
				var jsonObject = eval(jsonObject);
				var htmlString = "";
				//遍历数组中的元素
				$.each(jsonObject,function(i,n){
					htmlString +="<tr>";
					htmlString +="<td><img src='"+n.jd_img_src+"'></td>";
					htmlString +="<td>"+n.jd_title+"</td>";
					htmlString +="<td>"+n.jd_content+"</td>";
					htmlString +="<td>"+n.jd_price+"</td>";
					htmlString +="</tr>";
				});
				//将拼接好的字符串追加到tbody中
				$("#infoTBody").append(htmlString);
			}
		});
	</script>
</body>
</html>