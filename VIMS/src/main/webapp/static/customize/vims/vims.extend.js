(function($,window){
  
	// 扩展数组删除指定元素的方法
	Array.prototype.remove = function(val) {
	  var index = this.indexOf(val);
	  if (index > -1) {
	    this.splice(index, 1);
	  }
	};
	
	// 检索下标
	$.findMenuIndex=function(menus,stateName,compareAttr)
	{
		if(!stateName)return -1;
		var menuTabIndex=-1;
		for(var m in menus) { if(stateName===menus[m][compareAttr]) { menuTabIndex = m;break; } }	
		return Number(menuTabIndex);
	}
})(jQuery,window)