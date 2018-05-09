<%@ page language="java" contentType="text/html; charset=UTF-8 "  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
   <div class="modal-header">
		<button type="button" class="close" ng-click="close()">&times;</button>
		<h4 class="modal-title">新增</h4>
	</div>
	<div class="modal-body" >
		<form class="bs-example bs-example-form" role="form">
			<div class="row">
				<div class="col-sm-6">
					<div class="row m0">
						<div class="col-sm-4 form-title">操作用户</div>
						<div class="col-sm-8 p0">
							<input type="text" class="form-control" placeholder="操作用户">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">姓名</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="姓名">
							</div>
						</div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">所属部门</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="所属部门">
							</div>
						</div>
				</div>
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">性别</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="性别">
							</div>
						</div>	
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">角色</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="角色">
							</div>
						</div>	
				</div>
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">状态</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="状态">
							</div>
						</div>	
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-6">
					<div class="row m0">
							<div class="col-sm-4 form-title">电话</div>
							<div class="col-sm-8 p0">
								<input type="text" class="form-control" placeholder="电话">
							</div>
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
