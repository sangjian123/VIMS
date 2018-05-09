;
(function($, app) {
	"use strict";
	
	app.controller("RoleManagerCtrl", function($scope, $http,$state,$modal) {
		$scope.options={};
		$scope.options.url=basePath + "/static/customize/vims/service/support/role.json";
		$scope.options.colums=[
                   	{ title:'全选', field:'select', checkbox:true, width:25, align:'center', valign:'middle' },
                   	{ title:'ID', field:'ID', visible:false },
                	{ title:'名称', field:'roleName'},
                	{ title:'描述', field:'roleDescription', sortable:true },
                	{ title:'所属部门', field:'department', sortable:true },
                	{ title:'类型', field:'roleType', },
                	{ title:'创建日期', field:'createTime', sortable:true }
            	]
		$scope.addRole=function(){
			$modal.open({
	          	templateUrl:basePath + "/roleInfo",
	          	scope:false,
	          	backdrop:'static',
	          	keyboard:true,
	          	size:"defalut",
	          	controller:function($scope,$modalInstance){
	          		$scope.close=function(){ $modalInstance.dismiss('cancel'); }
	          	}
	          });
		}
	})
})(jQuery, app)