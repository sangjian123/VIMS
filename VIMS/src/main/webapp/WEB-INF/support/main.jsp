<%@ page language="java" contentType="text/html; charset=UTF-8 "  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html ng-app="main.webapp">
<head>
<%@ include file="../common/header.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>    
<body ng-controller="SupportCtrl">
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
	<div class="row full-height">  
        <div class="left-menu full-height">  
            <div class="panel-group accordion" id="accordion">  
            	<div class="nav-title">导航菜单</div>
                <div class="panel panel-default" ng-repeat="menu in menus">  
	                    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" href="#collapse_{{menu.id}}"  ng-class="{false:'collapsed'}[$index == 0]">  
                   			<h4 class="panel-title"> 
                   				<span class="glyphicon glyphicon-th-large"></span>{{menu.name}}
                   				<span class="earrow  chevron-down accordion-expand"></span>
                   				<span class="earrow  chevron-up accordion-collapse"></span>
                   			</h4>  
	                    </div> 
                    <div id="collapse_{{menu.id}}" class="panel-collapse collapse" ng-class="{true:' in'}[$index == 0]">  
                        <div class="panel-body">  
                            <table class="table menu-table">
                                <tr ng-repeat="child in menu.children" ng-click="tabs(child)">  
                                    <td><span class="glyphicon glyphicon-fire text-primary"></span><a href="#" ng-bind="child.name"></a></td>  
                                </tr>  
                            </table>  
                        </div>  
                    </div>  
                </div>  
            </div>  
        </div>  
        <div class="right-content full-height">  
        	<ul class="nav nav-tabs support-nav" ng-init="param.active=-1">
				<li ng-class="{true:'active'}[param.active==-1]"><a ng-click="goState();param.active=-1"><span class="tabs-icon glyphicon glyphicon-home"></span>首页</a></li>
				<li ng-class="{true:'active'}[param.active==$index]" ng-repeat="tab in tabsContents"><a ng-click="goState(tab.stateName);param.active=$index">{{tab.name}}</a><em class="em-close" ng-click="closeTab(tab,$index)">&times;</em></li>
			</ul>
			<div class="tab-content">
				<div  class="tab-pane fade in active" ui-view="content" ></div>
			</div>
        </div>  
    </div> 
</div>
<div class="body-bottom">
		<p>中软电网公司 版权所有 Copyright © 2010-2018 Chinasoft Grid Corporation. All rights reserved</p>
		<p>公司网址: www.chinasofti.com 客服电话：025-88888888</p>
</div>
</body>
</html>
