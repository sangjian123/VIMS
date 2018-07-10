;(function($, app) {
	"use strict";
	
	app.controller("VideoPlaybackCtrl", function($scope, $http,$messager,$sdkViewer) {
		$http({
        	method: 'POST',
        	url: basePath + '/device/query'
		})
		.success(function(data) {
			$scope.tree=toList(data.obj.list);
		});
		
		function toList(list){
			var temp=[];
			angular.forEach(list,function(v,i){
				listForEach(v,temp);
			})
			return temp;
		}
		
		function listForEach(v,temp){
			if(v.children)
			{
				angular.forEach(v.children,function(m,d){
					temp.push(m);
					listForEach(m,temp);
				})
			}	
		}
		
		var videoGroup={};// 存储回放视频对象集
		$scope.liveVideoScan=function($item,$event){
			if($item.children)return;
			if($item.$checked) { stopplayback($item); return; }
			if(validateRequire()) { queryVideo($item); } else { $item.$checked=false; }	
		}
		
		function validateRequire(){
			if(null==$scope.vedioType)
			{
				$messager.warning("提示","请选择类型！");
				return false;
			}	
			
			if(!$scope.starttime || !$scope.endtime)
			{
				$messager.warning("提示","请选择开始时间和结束时间！");
				return false;
			}	
			
			if($scope.starttime > $scope.endtime)
			{
				$messager.warning("提示","结束时间不能早于开始时间!");
				return false;
			}
			return true;
		}
		
		function queryVideo($item){
			// 设备登陆// 回放调用
			var _ResourceId = getResourceIdByVideoGroup($item);
			if(undefined==_ResourceId){
				$messager.error("提示","播放实况数大于分屏数，请先关闭部分视频。");
				return;
			}
			if(Cloudlogin($item,$item.url,$item.userName,$item.password) && Devlogin($item,$item.devName,$item.devPassword)) {
				CommnQuery($item,$scope.starttime,$scope.endtime,$scope.vedioType,_ResourceId);
			}		
			else{$item.$checked=false;}
		}
		
		/******************** 回放 ***************************/
		function getLocalTime(nS){     
			return new Date(parseInt(nS) * 1000).toLocaleString().substr(0,17)   
								} 
		
		var EventType ={
				ALL:                       0,        // 所有的存储
				MOTIONDETECTION:           4,        // 运动检测事件存储
				DIGITALINPUT:              5,        // 数字输入事件存储 
				VIDEOLOSS:                 7,        // 视频丢失事件存储
				INVALID:                   0xFF      // 无效值
			}  
		
		var Protocal = {
				TRANSPROTOCAL_RTPTCP:      1,         //TCP
				TRANSPROTOCAL_RTPUDP:      2          // UDP
			 }
		
		var PlayControl ={
			    NETDEV_PLAY_CTRL_PLAY:             0,           /* 开始播放  Play */
				NETDEV_PLAY_CTRL_PAUSE:            1,           /* 暂停播放  Pause */
				NETDEV_PLAY_CTRL_RESUME:           2,           /* 恢复播放  Resume */
				NETDEV_PLAY_CTRL_GETPLAYTIME:      3,           /* 获取播放进度  Obtain playing time */
				NETDEV_PLAY_CTRL_SETPLAYTIME:      4,           /* 设置播放进度  Configure playing time */
				NETDEV_PLAY_CTRL_GETPLAYSPEED:     5,           /* 获取播放速度  Obtain playing speed */
				NETDEV_PLAY_CTRL_SETPLAYSPEED:     6,           /* 设置播放速度  Configure playing speed */
				NETDEV_PLAY_CTRL_SINGLE_FRAME:     7            /* 设置单帧播放  Configure single frame playing speed */
			}	
		
		var VideoParam={
				VIDEOPATH:"C:\\NETDEV_\\DownLoad\\"
		}
		
		var MediaFileFormat = {
				MEDIA_FILE_MP4:            0,           // mp4格式的媒体文件
				MEDIA_FILE_TS:             1            // TS格式的媒体文件  TS media file */
			 }
		
		var videoRate = {
				NETDEV_PLAY_STATUS_16_BACKWARD : 0,//16倍后退播放
				NETDEV_PLAY_STATUS_8_BACKWARD : 1,//8倍后退播放
				NETDEV_PLAY_STATUS_4_BACKWARD : 2,//4倍后退播放
				NETDEV_PLAY_STATUS_2_BACKWARD : 3,//2倍后退播放
				NETDEV_PLAY_STATUS_1_BACKWARD : 4,//正常速度后退播放
				NETDEV_PLAY_STATUS_HALF_BACKWARD : 5,//1/2倍后退播放
				NETDEV_PLAY_STATUS_QUARTER_BACKWARD : 6,//1/4倍后退播放
				NETDEV_PLAY_STATUS_QUARTER_FORWARD : 7,//1/4倍播放
				NETDEV_PLAY_STATUS_HALF_FORWARD : 8,//1/2倍播放
				NETDEV_PLAY_STATUS_1_FORWARD : 9,//正常速度播放
				NETDEV_PLAY_STATUS_2_FORWARD : 10,//2倍速度播放
				NETDEV_PLAY_STATUS_4_FORWARD : 11,//4倍速度播放
				NETDEV_PLAY_STATUS_8_FORWARD : 12,//8倍速度播放
				NETDEV_PLAY_STATUS_16_FORWARD : 13//16倍速度播放
		}
		
		$scope.vedioTypeList=[
		                      {key:'所有事件',value:EventType.ALL}, 
		                      {key:'运动检测事件',value:EventType.MOTIONDETECTION}, 
		                      {key:'数字输入事件',value:EventType.DIGITALINPUT}, 
		                      {key:'视频丢失事件',value:EventType.VIDEOLOSS}];
		
		$scope.vedioType = EventType.ALL;
		$scope.starttime=new Date().format("yyyy-MM-dd");
		$scope.endtime=new Date().format("yyyy-MM-dd");
		
		var $sdk=$sdkViewer.initSdkViewer("videoScreen");
		var sdk_viewer = $sdk.sdk;
		
		$scope.splitScreen=function(splitNum){
			var retcode = sdk_viewer.execFunction("NetSDKSetPlayWndNum" , splitNum);         //分屏 
	        if(0!=retcode){
	        	$messager.error("提示","实况窗口实例化失败");
	        }
		}
		$scope.splitScreen(4);// 分屏
		 // 云登陆
        function Cloudlogin($item,cameraIp,userName,password)
    	{
    		var SDKRet = sdk_viewer.execFunction("NETDEV_LoginCloud", cameraIp,userName, password);
    		if(-1 == SDKRet)
    		{
    			$messager.error("提示","云登录失败");
    			return false;
    		}
            else{
            	$item.CloudHandle = SDKRet;
            	return true;
    		}
    	}
        
        // 设备登陆
        function Devlogin($item,cloudDevName,password)
    	{
    		var dataMap = { szDeviceName:cloudDevName, szDevicePassword:password, dwT2UTimeout:0 }
            var jsonStr = JSON.stringify(dataMap);
    		var SDKRet = sdk_viewer.execFunction("NETDEV_LoginCloudDev", $item.CloudHandle || -1, jsonStr);
    		if(-1 == SDKRet)
    		{
    			$messager.error("提示","设备登录失败");
    			return false;
    		}
            else{
    		    var result = JSON.parse(SDKRet);
    		    $item.DeviceHandle = result.UserID;	
    	        return true;
    		}
    	}
		
		function CommnQuery($item,starttime,endtime,eventType,ResourceId){
			var vEndTime = (new Date((starttime + " 00:00:00").replace(/-/g,"/")).getTime())/1000;
			var vBeginTime = (new Date((endtime + " 23:59:59").replace(/-/g,"/")).getTime())/1000;
			var dataMap = {
						  szFileName:0,
	                      dwChannelID:$item.channelID || 1, 
	                      dwFileType:eventType,
						  tBeginTime:vBeginTime,
						  tEndTime:vEndTime
	                      }

	        var jsonStr = JSON.stringify(dataMap);
		    var SDKRet = sdk_viewer.execFunction("NETDEV_FindFile",$item.DeviceHandle,jsonStr);
			if(-1 != SDKRet)
			{
				console.log("获取视频回放文件...");
				FindNextFile($item,SDKRet,ResourceId);
			}
			else{
				$messager.error("提示","找不到文件");
			}
	    }
		
		function FindNextFile($item,handle,ResourceId)
		{
			var SDKRet = sdk_viewer.execFunction("NETDEV_FindNextFile",handle);
			if(-1 == SDKRet)return;
			var result = JSON.parse(SDKRet);
			$item.vBeginTime = result.tBeginTime;
			$item.vEndTime = result.tEndTime;
			playbackbytime($item,ResourceId);
			// FindNextFile($item,handle,ResourceId);
		}
		
		// 查找分屏object中有空余的未播放屏
        function getResourceIdByVideoGroup($item){
        	var wnd = sdk_viewer.execFunction("NetSDKGetPlayWndNum");
        	for(var index=0;index < wnd;index++)
        	{
        		// 如果有未播放的屏，设置当前屏下标给$item作为标记
        		if(!videoGroup[index])
        		{
        			$item.videoReSourceId=index;
        			$item.$checked=true;
        			break;
        		}	
        	}	
        	return $item.videoReSourceId;
        }
		
        function createTimeScroll(){
        	var tempResourceId,timeScroll;
        	setInterval(function(){
        		var ResourceId = sdk_viewer.execFunction("NetSDKGetFocusWnd");
        		var $$video = videoGroup[ResourceId];
        		if(!$$video) { $(".progressTime").hide(); return; }	
        		$(".progressTime").show();
        		if(tempResourceId != ResourceId)
        		{
        			// 生成时间抽
        			timeScroll=new TimeScroll(
        			function(current,startTime,endTime,exec){
        				var ResourceId = exec._viewer.execFunction("NetSDKGetFocusWnd");
        				var t = new Date(current).getTime() / 1000;
        				var dataMap = { pulTime:t, pulSpeed:0 }
        				var jsonStr = JSON.stringify(dataMap);
        				exec._viewer.execFunction("NETDEV_PlayBackControl",parseInt(ResourceId),exec._videoType ,jsonStr);
        			},
        			new Date($$video.vBeginTime*1000).format("yyyy/MM/dd hh:mm:ss"), 
        			new Date($$video.vEndTime*1000).format("yyyy/MM/dd hh:mm:ss"),
        			{_viewer:sdk_viewer,_videoType:PlayControl.NETDEV_PLAY_CTRL_SETPLAYTIME});
        			
        			tempResourceId=ResourceId;
        		}	
    			var time = GetProgress(ResourceId);
    			timeScroll.updateTime(Math.abs(time - $$video.vBeginTime * 1000) / 1000);
        	},1000);
        }
        
        function GetProgress(ResourceId){
			var dataMap = {
						  pulTime:0,
						  pulSpeed:0
	                      }
			var jsonStr = JSON.stringify(dataMap);
		    var SDKRet = sdk_viewer.execFunction("NETDEV_PlayBackControl",parseInt(ResourceId),PlayControl.NETDEV_PLAY_CTRL_GETPLAYTIME ,jsonStr);
			if(-1 != SDKRet)
			{
				var result = JSON.parse(SDKRet);
			    var PlayTime = result.PlayTime;
			    return PlayTime * 1000;
			}
			return new Date().getTime();
	    }
        
        if(_IntervalcreateTimeScroll){clearInterval(_IntervalcreateTimeScroll)};
        var _IntervalcreateTimeScroll;
		function playbackbytime($item,ResourceId){
			var dataMap = {
						  dwChannelID:$item.channelID || 1, 
						  tBeginTime:$item.vBeginTime,
						  tEndTime:$item.vEndTime,
						  dwLinkMode:Protocal.TRANSPROTOCAL_RTPTCP,
						  dwFileType:$scope.vedioType,
						  dwDownloadSpeed:3
	                      }
	        var jsonStr = JSON.stringify(dataMap);
		    var retcode = sdk_viewer.execFunction("NETDEV_PlayBack", parseInt(ResourceId), $item.DeviceHandle,jsonStr);
			if(-1 == retcode)
			{
				$messager.error("提示","回放失败");
				$item.$checked=false;
				return;
			}
			
			// 获取该视频当前开始时间
			if(_IntervalcreateTimeScroll){clearInterval(_IntervalcreateTimeScroll);} 
			_IntervalcreateTimeScroll = createTimeScroll();
			videoGroup[ResourceId]=$item;
	    }
		
		function stopplayback($item){
			var ResourceId = $item.videoReSourceId;
		    var retcode = sdk_viewer.execFunction("NETDEV_StopPlayback", ResourceId);
			if(0 != retcode)
			{
				$messager.error("提示","停止回放失败");
				return;
			}
			$item.$checked=false;
	        videoGroup[ResourceId]=null;
	    }
		
		$scope.downloadbytime=function(e){
			var ResourceId = sdk_viewer.execFunction("NetSDKGetFocusWnd");
			var $$video = videoGroup[ResourceId];
			if(!$$video)return;
			var dataMap = {
						  dwChannelID:$$video.channelID || 1, 
						  tBeginTime:$$video.vBeginTime,
						  tEndTime:$$video.vEndTime,
						  dwLinkMode:Protocal.TRANSPROTOCAL_RTPTCP,
						  dwFileType:$scope.vedioType,
						  dwDownloadSpeed:3
	                      }
			var jsonStr = JSON.stringify(dataMap);
		    var retcode = sdk_viewer.execFunction("NETDEV_GetFileByTime",$$video.DeviceHandle ,jsonStr , VideoParam.VIDEOPATH + $$video.devName + "_" + new Date().getTime(), MediaFileFormat.MEDIA_FILE_MP4);
			if(-1 == retcode)
			{
				$messager.error("提示","下载失败");
			}
			$$video.DownLoadHandle = retcode;
	    }
		
		$scope.stopdownload=function(e){
			var ResourceId = sdk_viewer.execFunction("NetSDKGetFocusWnd");
			var $$video = videoGroup[ResourceId];
			if(!$$video)return;
		    var retcode = sdk_viewer.execFunction("NETDEV_StopDownload",$$video.DownLoadHandle);
			if(0 != retcode)
			{
				$messager.error("提示","下载保存失败");
				return;
			}
			$messager.warning("提示","下载成功! 文件路径 " + VideoParam.VIDEOPATH);
	    }
		
		$scope.SetSpeed=function(e,speed){
			var ResourceId = sdk_viewer.execFunction("NetSDKGetFocusWnd");
			var $$video = videoGroup[ResourceId];
			if(!$$video)return;
			var dataMap = {
							pulTime:0,
						  	pulSpeed:speed
	                      }
			var jsonStr = JSON.stringify(dataMap);
		    var SDKRet = sdk_viewer.execFunction("NETDEV_PlayBackControl",parseInt(ResourceId),PlayControl.NETDEV_PLAY_CTRL_SETPLAYSPEED ,jsonStr);
			if(-1 == SDKRet)
			{
				$messager.error("提示","设置播放速度失败");
			}
	    }
		
		$scope.play=function(e,switchBtn)
		{
			var ResourceId = sdk_viewer.execFunction("NetSDKGetFocusWnd");
			var $$video = videoGroup[ResourceId];
			if(!$$video)return;
			var dataMap = {
						  pulTime:0,
						  pulSpeed:0
	                      }
			var jsonStr = JSON.stringify(dataMap);
		    var SDKRet = sdk_viewer.execFunction("NETDEV_PlayBackControl",parseInt(ResourceId),switchBtn ? PlayControl.NETDEV_PLAY_CTRL_RESUME : PlayControl.NETDEV_PLAY_CTRL_PAUSE ,jsonStr);
			if(-1 == SDKRet)
			{
				$messager.error("提示","重播失败");
			}
		}
	})
})(jQuery, app)