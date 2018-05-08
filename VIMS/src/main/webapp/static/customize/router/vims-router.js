var app = angular.module("vims.webapp", ["ui.bootstrap","ui.router"]);
;(function($, app) {
	  "use strict";

	  app.config(function ($stateProvider,$locationProvider,$urlRouterProvider) {
		  var configs = [];
		  // 主页
		  configs.push({
			  stateName:"vimsportal",
			  stateConfig:{
				     url: '/list',
			    	 views: {
			    		  "content@":{
			    		 	    template: '',
			    		 	    controller:function($scope,$state){
			    		 	    	$state.go("vimsportal.home",{isRouter:true});
			    		 	    }
			    		 	}
			            }
			     }
		  });
		  
		  // 首页
		  configs.push({
			  stateName:"vimsportal.home",
			  stateConfig:{
				     url: '/0',
				     params:{isRouter:null},
			    	 views: {
			    		  "content@":{
			    		 	    template: '<h1 class="masked position-relative-center-h1">欢迎来到VIMS系统运营</h1>'
			    		 	}
			            }
			     }
		  });
		  
		  // 基础信息
		  configs.push({
			  stateName:"vimsportal.basicInfo",
			  stateConfig:{
				     url: '/1',
				     params:{isRouter:null},
			    	 views: {
			    		  "content@":{
			    		 	    templateUrl: basePath + "/basicInfo",
			    		 	    controller:"BasicInfoCtrl"
			    		 	}
			            }
			     }
		  });
		  
		  // 管理调度
		  configs.push({
			  stateName:"vimsportal.managementSchedue",
			  stateConfig:{
				     url: '/2',
				     params:{isRouter:null},
			    	 views: {
			    		  "content@":{
			    		 	    templateUrl: basePath + "/managementSchedue",
			    		 	    controller:"ManagementSchedueCtrl"
			    		 	}
			            }
			     }
		  });
		  
		  // 系统支撑==================================================================
		  
		  // 主页
		  configs.push({
			  stateName:"vimssupport",
			  stateConfig:{
				     url: '/backstage',
			    	 views: {
			    		  "content@":{
			    		 	    template: '',
			    		 	    controller:function($scope,$state){
			    		 	    	$state.go("vimssupport.home",{isRouter:true});
			    		 	    }
			    		 	}
			            }
			     }
		  });
		  
		  // 首页
		  configs.push({
			  stateName:"vimssupport.home",
			  stateConfig:{
				     url: '/0',
				     params:{isRouter:null},
			    	 views: {
			    		  "content@":{
			    		 	    template: '<h1 class="masked position-relative-center-h1">欢迎来到VIMS系统运营支撑</h1>'
			    		 	}
			            }
			     }
		  });
		  
		 // 角色管理
		  configs.push({
			  stateName:"vimssupport.roleManager",
			  stateConfig:{
				     url: '/1',
				     params:{isRouter:null},
			    	 views: {
			    		  "content@":{
			    			  templateUrl: basePath + "/roleManager",
			    			  controller:"RoleManagerCtrl"
			    		 	}
			            }
			     }
		  });
		  
		 // 部门管理
		  configs.push({
			  stateName:"vimssupport.deptManager",
			  stateConfig:{
				     url: '/2',
				     params:{isRouter:null},
			    	 views: {
			    		  "content@":{
			    			  templateUrl: basePath + "/deptManager",
			    			  controller:"DeptManagerCtrl"
			    		 	}
			            }
			     }
		  });
		  
	  angular.forEach(configs,function(v,i){
		  $stateProvider.state(v.stateName,v.stateConfig);
	  })
	});
	  
	  app.run(['$rootScope', '$state', '$timeout', function($rootScope, $state, $timeout) {
	  $rootScope.$on('$stateChangeStart', function(event, toState, toStateParams) {
		  if(toStateParams.hasOwnProperty("isRouter") && !toStateParams.isRouter)event.preventDefault();
	  });
  }]);  
	})(jQuery, app)
