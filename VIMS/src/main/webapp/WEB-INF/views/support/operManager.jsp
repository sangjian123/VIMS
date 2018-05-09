<%@ page language="java" contentType="text/html; charset=UTF-8 "  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
 <div class="vims-body">
	  	<vims-table vims-url="options.url"  vims-columns="options.colums" vims-options="toolbar:'#toolbar',method:'get'"></vims-table>
	  	<div id="toolbar" class="btn-group pull-right">
				<button id="btn_add" type="button" class="btn btn-default" ng-click="addOperator()">
		            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		        </button>
		        <button id="btn_edit" type="button" class="btn btn-default">
		            <span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改
		        </button>
		        <button id="btn_delete" type="button" class="btn btn-default">
		            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		        </button>
		  </div>
	  </div>
</body>
</html>
