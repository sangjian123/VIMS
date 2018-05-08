<%@ page language="java" contentType="text/html; charset=UTF-8 "  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html ng-app="main.webapp">
<head>
<%@ include file="../common/header.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>    
<body ng-controller="HomeCtrl">
<div class="body-header">
	<div class="logo fl"></div>	
	<div class="list fr">
		<ul>
			<li><a href="#">用户注册</a></li>
			<li><a href="#">个人信息</a></li>
			<li><a href="#">语言选择</a></li>
			<li><a href="#">安全退出</a></li>
		</ul>
	</div>	
</div>
<div class="container body-content">  
	<div class="full-height">
		<ul class="nav nav-tabs" ng-init="param.active=-1">
			<li ng-class="{true:'active'}[param.active==-1]"><a ng-click="goState('vimssupport.home');param.active=-1"><span class="tabs-icon glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a></li>
			<li ng-class="{true:'active'}[param.active==$index]" ng-repeat="tab in tabsContents"><a ng-click="goState(tab.stateName);param.active=$index" ng-bind="tab.name"></a></li>
		</ul>
		<div class="tab-content">
			<div  class="tab-pane fade in active" ui-view="content" ></div>
		</div>
	</div>
</div>
<div class="body-bottom">
		<p>中软电网公司 版权所有 Copyright © 2010-2018 Chinasoft Grid Corporation. All rights reserved</p>
		<p>公司网址: www.chinasofti.com 客服电话：025-88888888</p>
</div>
</body>
</html>
