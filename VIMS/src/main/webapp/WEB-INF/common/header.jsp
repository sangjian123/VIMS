<%@ page language="java" contentType="text/html; charset=UTF-8 "  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<title>VIMS系统</title>
<c:set var="basePath" value="<%=request.getContextPath () %>"></c:set>
<link rel="shortcut icon" href="${basePath}/static/customize/images/favicon.ico" />
<!-- 公用插件样式表 -->
<link rel="stylesheet" type="text/css" href="${basePath}/static/common/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/static/common/bootstrap/bootstrap.tree.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/static/common/bootstrap/bootstrap.select.min.css" />
<!-- VIMS定制 -->
<link rel="stylesheet" type="text/css" href="${basePath}/static/customize/css/vims.css" />
<!-- 公用插件及Jquery库 -->
<script type="text/javascript" src="${basePath}/static/common/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/common/bootstrap/bootstrap.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/common/angular/angular.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/common/angular/angular-ui-router.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/common/angular/ui-bootstrap-tpls-0.11.2.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/common/bootstrap/bootstrap.select.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/common/bootstrap/bootstrap-paginator.min.js" charset="utf-8"></script>
<script type="text/javascript">
var basePath = '${basePath}';
document.oncontextmenu = function(){return false;}
</script>
<!-- APP页面系统定制js -->
<script type="text/javascript" src="${basePath}/static/customize/router/vims-directive.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/customize/router/vims-router.js" charset="utf-8" ></script>
<script type="text/javascript" src="${basePath}/static/customize/vims/vims.extend.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/customize/vims/vims-all.js" charset="utf-8"></script>