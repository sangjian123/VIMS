;
(function($, app) {
	"use strict";
	
	app.controller("SupportCtrl", function($scope, $http,$state) {
		var firstPage = "vimssupport";
		
		$scope.menus =[
		                 {id:'1000',name:'组织与权限',children:[
		                                                   {id:'10001',name:'角色管理',stateName:'vimssupport.roleManager'},
		                                                   {id:'10002',name:'部门管理',stateName:'vimssupport.deptManager'},
		                                                   {id:'10003',name:'操作员管理',stateName:'vimssupport.operManager'},
		                                                   {id:'10004',name:'工作流管理',stateName:'vimssupport.workflowManager'}
		                                                   ]}
		                 ];
		  $scope.tabsContents=[];
		  $scope.param={};
		  $scope.tabs=function(tab)
		  {
			  if($scope.tabsContents.indexOf(tab) <= -1) { $scope.tabsContents.push(tab); }	  
			  $scope.param.active = $scope.tabsContents.indexOf(tab);
			  $scope.goState(tab.stateName);
		  }
		  $scope.closeTab=function(tab,index)
		  {
			  if(index <= $scope.param.active)$scope.param.active--;
			  $scope.tabsContents.remove(tab);
			  var tab = $scope.tabsContents[$scope.param.active];
			  $scope.goState(tab ? tab.stateName : firstPage);
		  }
		  $scope.goState=function(stateName) { $state.go(stateName ? stateName : firstPage,{isRouter:true}); }
		  $scope.goState(firstPage);// 默认跳转首页
	})
})(jQuery, app)