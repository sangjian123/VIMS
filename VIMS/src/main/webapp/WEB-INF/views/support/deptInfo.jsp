<%@ page language="java" contentType="text/html; charset=UTF-8 "  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
   <div class="modal-header">
		<button type="button" class="close" ng-click="close()">&times;</button>
		<h4 class="modal-title">部门信息</h4>
	</div>
	<div class="modal-body" >
		<form class="bs-example bs-example-form" role="form">
			<div class="row">
				<div class="col-sm-6">
					<div class="row m0">
						<div class="col-sm-4 form-title">部门名称</div>
						<div class="col-sm-8 p0">
							<input type="text" class="form-control" placeholder="部门名称">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">固定电话</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="固定电话">
							</div>
						</div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">部门传真</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="部门传真">
							</div>
						</div>
				</div>
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">上级部门</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="上级部门">
							</div>
						</div>	
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">部门邮箱</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="部门邮箱">
							</div>
						</div>	
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-12">
					<div class="input-group">
						<span class="input-group-addon">备注</span>
						<textarea class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
	<button type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-ok"></span>&nbsp;保存</button>
			<button type="button" class="btn btn-primary btn-sm"  ng-click="close()"><span class="glyphicon glyphicon-remove"></span>&nbsp;取消</button>
	</div>
</body>
</html>
