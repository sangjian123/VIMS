;
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
})(jQuery, app)