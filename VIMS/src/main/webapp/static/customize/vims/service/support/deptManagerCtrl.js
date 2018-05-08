;
(function($, app) {
	"use strict";
	
	app.controller("DeptManagerCtrl", function($scope, $http,$state,$modal) {
		$scope.options={};
		$scope.options.url=basePath + "/static/customize/vims/service/support/data.json";
		$scope.options.colums=[
                   	{ title:'全选', field:'select', checkbox:true, width:25, align:'center', valign:'middle' },
                   	{ title:'ID', field:'ID', visible:false },
                	{ title:'部门名称', field:'deptName'},
                	{ title:'固定电话', field:'telephone', sortable:true },
                	{ title:'部门传真', field:'deptFax', sortable:true },
                	{ title:'上级部门', field:'superDeptName', },
                	{ title:'部门邮箱', field:'deptEmail' },
                	{ title:'创建日期', field:'createTime', sortable:true },
                	{ title:'备注', field:'mark', align:'center' }
            	]
		$scope.addDept=function(){
			$modal.open({
	          	templateUrl:basePath + "/deptInfo",
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