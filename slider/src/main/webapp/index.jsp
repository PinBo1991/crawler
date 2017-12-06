<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>滑动验证码测试</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE-edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<link href="resources/css/slide-unlock.css" rel="stylesheet">
	<style>
	    html, body, h1 {
	        margin: 0;
	        padding: 0;
	    }
	    body {
	        background-color: #393939;
	        color: #d5d4ff;
	        overflow: hidden
	    }
	    #demo {
	        width: 600px;
	        margin: 150px auto;
	        padding: 10px;
	        border: 1px dashed #d5d4ff;
	        border-radius: 10px;
	        -moz-border-radius: 10px;
	        -webkit-border-radius: 10px;
	        text-align: left;
	    }
	</style>
</head>
<body>
	<div id="demo">
		<div id="slider">
			<div id="slider_bg"></div>
			<span id="label">>></span> <span id="labelTip">拖动滑块验证</span> 
		</div>
		<script src="resources/js/jquery-1.12.1.min.js"></script> 
		<script src="resources/js/jquery.slideunlock.js"></script> 
		<script>
			$(function () {
				var slider = new SliderUnlock(
					"#slider",
					{successLabelTip : "欢迎访问工程"},
					function(){ alert("验证成功,即将跳转"); window.location.href="http://192.168.10.153/index02.jsp"}
				);
				slider.init();
			})
		</script> 
	</div>
</body>
</html>