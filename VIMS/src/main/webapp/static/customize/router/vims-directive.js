(function($){
	"use strict";
	
	var app = angular.module("main.webapp", ['vims.webapp']);
	
	app.config(["$httpProvider", function ($httpProvider) {   
        $httpProvider.interceptors.push('vimsInterceptor');  
    }]);
	
	app.factory('vimsInterceptor', function ($rootScope,$q,$injector) {return { 
		request: function (config) { 
			return config;
		}, 
		response: function (response) {
			return response; 
			}};
		});  
	
	app.directive('vimsTable', function($timeout,$compile) {
        return {
        	restrict: 'E',
            scope:{
            	vimsUrl:'=vimsUrl',
            	vimsColumns:'=vimsColumns',
            	vimsOptions:'@vimsOptions',
            },
            template: '<table class="table table-hover"></table>',
            replace: true,
            link:function(scope,element,attr)
            {
            	var options = {
                    	url:scope.vimsUrl,
                    	striped: true, //是否显示行间隔色
                    	pagination:true,//是否分页
                    	queryParams:[],
                    	sidePagination:'server',
                    	showRefresh:true,//刷新按钮
                    	showColumns:true,
                    	clickToSelect: false,//是否启用点击选中行
                    	columns:scope.vimsColumns
                    };
            	var option = scope.vimsOptions ? (new Function('return ' + '{' + scope.vimsOptions + '}'))() : {};
            	$.extend(options,option);
            	$(element).bootstrapTable(options);
            }
        };
    })
})(jQuery)