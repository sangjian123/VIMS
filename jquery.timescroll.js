(function (window, document,$) {
	
	var TimeScroll = function(fun, startTime, endTime,exec){
		if (!(this instanceof TimeScroll)) return new TimeScroll(fun, startTime, endTime,exec);
		this.fun=fun;
		this.startTime=startTime;
		this.endTime=endTime;
		this.exec=exec;// 扩展
		this.ProgressTime();
	};
	
    //滑块
	var ScrollBar = {
	    value: 0,
	    maxValue: 40,
	    step: 1,
	    currentX: 0,
	    Initialize: function (callback,startTime,endTime,exec) {
	        if (this.value > this.maxValue) {
	            alert("给定当前值大于了最大值");
	            return;
	        }
	        this.GetValue();
	        $("#scroll_Track").css("width", this.currentX + "px");
	        $("#scroll_Thumb").css("margin-left", this.currentX + "px");
	        this.Value(callback,startTime,endTime,exec);
	    },
	    SetValue: function (aValue) {
	        this.value = aValue;
	        if (this.value >= this.maxValue) this.value = this.maxValue;
	        if (this.value <= 0) this.value = 0;
	        var mWidth = this.value / this.maxValue * $("#scrollBar").width() + "px";
	        $("#scroll_Track").css("width", mWidth);
	        $("#scroll_Thumb").css("margin-left", mWidth);
	    },
	    Value: function (callback,startTime,endTime,exec) {
	        var valite = false;
	        var currentValue;
	        // 点击进度条时滑块到达对应位置
	        $("#scrollBarBox").unbind("click").bind("click",function (event) {
	            var changeX = event.clientX - ScrollBar.currentX;
	            currentValue = changeX - ScrollBar.currentX - $("#scrollBar").offset().left;
	            $("#scroll_Thumb").css("margin-left", currentValue + "px");
	            $("#scroll_Track").css("width", currentValue + 2 + "px");
	            if ((currentValue + 1) >= $("#scrollBar").width()) {
	                $("#scroll_Thumb").css("margin-left", $("#scrollBar").width() - 1 + "px");
	                $("#scroll_Track").css("width", $("#scrollBar").width() + 2 + "px");
	                ScrollBar.value = ScrollBar.maxValue;
	            } else if (currentValue <= 0) {
	                $("#scroll_Thumb").css("margin-left", "0px");
	                $("#scroll_Track").css("width", "0px");
	                ScrollBar.value = 0;
	            } else {
	                ScrollBar.value = Math.round(currentValue * ScrollBar.maxValue / $("#scrollBar").width());
	            }
	            SetTime(ScrollBar.value);
	            if (typeof (callback) == "function") {
	    	        var jscode = new Function('return ' + callback)();
	    	        jscode($(".timecode").html(),startTime,endTime,exec);
	    	    }
	        });
	        // 鼠标在进度条上面滑动时小滑块显示并对应相应的时间
	        $("#scrollBarBox").unbind("mousemove").bind("mousemove",function (event) {
	            var changeX = event.clientX;
	            currentValue = changeX - $("#scrollBar").offset().left;
	            $(".timecode").show().css("left", currentValue - 64 + "px");
	            if ((currentValue + 1) >= $("#scrollBar").width()) {
	                $(".timecode").css("left", $("#scrollBar").width() - 43 + "px");
	                ScrollBar.value = ScrollBar.maxValue;
	            } else if (currentValue <= 0) {
	                $(".timecode").css("left", "-64px");
	                ScrollBar.value = 0;
	            } else {
	                ScrollBar.value = Math.round(currentValue * ScrollBar.maxValue / $("#scrollBar").width());
	            }
	            SetTime1(ScrollBar.value);
	        });
	        // 鼠标移入进度条时小滑块显示
	        $("#scrollBarBox").unbind("mouseover").bind("mouseover",function (event) {
	            $(".timecode").show();
	        });
	        // 鼠标移除进度条时小滑块消失
	        $("#scrollBarBox").unbind("mouseout").bind("mouseout",function (event) {
	            $(".timecode").hide();
	        });
	    },
	    GetValue: function () {
	        this.currentX = $("#scrollBar").width() * (this.value / this.maxValue);
	    }
	}
	
	// 控制大滑块的当前时间
	function SetTime(value) {
	    var start = $("#startTime").html();
	    var startDate = new Date(Date.parse(start));
	    startDate.setSeconds(startDate.getSeconds() + 1 * value);//十五分钟为进度
	    var month = startDate.getMonth() + 1 < 10 ? "0" + (startDate.getMonth() + 1) : startDate.getMonth() + 1;
	    var currentDate = startDate.getDate() < 10 ? "0" + startDate.getDate() : startDate.getDate();
	    var Hours = startDate.getHours() < 10 ? "0" + startDate.getHours() : startDate.getHours();
	    var Minutes = startDate.getMinutes() < 10 ? "0" + startDate.getMinutes() : startDate.getMinutes();
	    var Seconds = startDate.getSeconds() < 10 ? "0" + startDate.getSeconds() : startDate.getSeconds();
	    var indexStart = startDate.getFullYear() + "/" + month + "/" + currentDate + " " + Hours + ":" + Minutes + ":" + Seconds;
	    $("#scroll_Thumb").html(indexStart);
	}
	// 控制小滑块的当前时间，小滑块时间变化时大滑块不变
	function SetTime1(value) {
	    var start = $("#startTime").html();
	    var startDate = new Date(Date.parse(start));
	    startDate.setSeconds(startDate.getSeconds() + 1 * value);//十五分钟为进度
	    var month = startDate.getMonth() + 1 < 10 ? "0" + (startDate.getMonth() + 1) : startDate.getMonth() + 1;
	    var currentDate = startDate.getDate() < 10 ? "0" + startDate.getDate() : startDate.getDate();
	    var Hours = startDate.getHours() < 10 ? "0" + startDate.getHours() : startDate.getHours();
	    var Minutes = startDate.getMinutes() < 10 ? "0" + startDate.getMinutes() : startDate.getMinutes();
	    var Seconds = startDate.getSeconds() < 10 ? "0" + startDate.getSeconds() : startDate.getSeconds();
	    var indexStart = startDate.getFullYear() + "/" + month + "/" + currentDate + " " + Hours + ":" + Minutes + ":" + Seconds;
	    $(".timecode").html(indexStart);
	}
	
	TimeScroll.prototype={
			constructor:this,
			updateTime:function(v){
				 ScrollBar.SetValue(v);
			},
			ProgressTime:function(){
			    var myfun = this.fun;
			    var startTime = this.startTime;
			    var endTime = this.endTime;
			    $("#progressTime").show();
			    $("#startTime").text(startTime);
			    $("#endTime").text(endTime);
			    //	SetTime(0);// 设置大滑块tip
			    // 结束时间
			    //	 SetTime1(0);// 设置小滑块tip
			    
			    //设置最大值
			    var qdsjDate = new Date(Date.parse(startTime));
			    var jssjDate = new Date(Date.parse(endTime));
			    ScrollBar.maxValue = Math.abs(qdsjDate - jssjDate) / 1000;
			    //初始化
			    ScrollBar.Initialize(myfun,this.startTime,this.endTime,this.exec);
			}
	}
	
	// 暴露方法  
    window.TimeScroll = TimeScroll;
})(window,document,jQuery);