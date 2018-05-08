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
})(jQuery)