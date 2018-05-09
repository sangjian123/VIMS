;
(function($, app) {
	"use strict";
	
	app.controller("OperatorManagerCtrl", function($scope, $http,$state,$modal) {
		$scope.options={};
		$scope.options.url=basePath + "/static/customize/vims/service/support/operator.json";
		$scope.options.colums=[
                   	{ title:'全选', field:'select', checkbox:true, width:25, align:'center', valign:'middle' },
                   	{ title:'ID', field:'ID', visible:false },
                	{ title:'操作用户', field:'operUserName'},
                	{ title:'姓名', field:'userName', sortable:true },
                	{ title:'所属部门', field:'department', sortable:true },
                	{ title:'性别', field:'sex'},
                	{ title:'电话', field:'phone'},
                	{ title:'角色', field:'roleName'},
                	{ title:'状态', field:'status' },
                	{ title:'创建日期', field:'createTime', sortable:true }
            	]
		$scope.addOperator=function(){
			$modal.open({
	          	templateUrl:basePath + "/operatorInfo",
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