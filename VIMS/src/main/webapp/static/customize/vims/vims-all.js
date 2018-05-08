;
(function($, app) {
	"use strict";
	
	app.controller("BasicInfoCtrl", function($scope, $http) {
		alert(1);
	})
})(jQuery, app);
(function($, app) {
	"use strict";
	
	app.controller("HomeCtrl", function($scope, $http,$state) {
		var firstPage = "vimsportal";
		$scope.tabsContents=[
		                     {name:"基本资料",stateName:"vimsportal.basicInfo"},
		                     {name:"管理调度",stateName:"vimsportal.managementSchedue"},
		                     {name:"车辆定位",stateName:""},
		                     {name:"历史轨迹",stateName:""},
		                     {name:"电子围栏",stateName:""},
		                     {name:"驻车防盗",stateName:""},
		                     {name:"告警消息",stateName:""},
		                     {name:"运营报表",stateName:""},
		                     {name:"客户服务",stateName:""}
		            ];
		$scope.goState=function(stateName) { $state.go(stateName,{isRouter:true}); }
		$scope.goState(firstPage);// 默认跳转首页
	})
})(jQuery, app);
(function($, app) {
	"use strict";
	
	app.controller("LoginCtrl", function($scope, $http) {
		$scope.user={};
		// 点击登陆跳转主页
		$scope.login=function(){
			if($scope.user.username == 'root')
			{
				window.location.href = basePath + "/support";
				return;
			}	
			window.location.href = basePath + "/home";
		}
	})
})(jQuery, app);
(function($, app) {
	"use strict";
	
	app.controller("ManagementSchedueCtrl", function($scope, $http) {
		alert(2);
	})
})(jQuery, app);
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