<%@ page language="java" contentType="text/html; charset=UTF-8 "  pageEncoding="UTF-8" %>
<html ng-app="main.webapp">
<head>
<%@ include file="../common/header.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${basePath}/static/customize/css/login.css" type="text/css" media="all" />
</head>
<body>
	<div class="container" ng-controller="LoginCtrl">
	<h1 style="margin-top: 15%">VIMS后台管理系统</h1>
		<div class="signin">
	     	<form>
		      	<input type="text" class="user"  ng-model="user.username"  placeholder="请输入用户名" />
		      	<input type="password" class="pass"  ng-model="user.password"  placeholder="请输入密码"  autocomplete = 'new-password'/>
		      	<label class="fr">
			  		<a>忘记密码?</a>
			  	</label>
	          	<input type="button" class="loginBtn" value="登录" ng-click="login()"/>
		 	</form>
		</div>
	</div>
	<div class="footer">
	     <p>Copyright &copy; 2018 VIMS Login Form. All Rights Reserved | Design by W3C Shcool</p>
	</div>
</body>
</html>