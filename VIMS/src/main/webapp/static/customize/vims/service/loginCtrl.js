;
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
})(jQuery, app)