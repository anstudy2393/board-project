<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<%-- 우측 상단 짝대기 3개 --%>
				</button>
				<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<%-- 메인을 누르면 메인화면으로 이동 --%>
				<li><a href="bbs.jsp">게시판</a></li>
				<%-- 게시판을 누르면 게시판 화면으로 이동 --%>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<%-- #은 현재 가르키고 있는 링크가 없다는 것을 의미함 / caret은 하나의 아이콘 같은 것 --%>
				</li>
			</ul>
			<%--list 같은 것을 보여줄 때 사용 --%>
		</div>
	</nav>
	<script src="https://code.jquery.com/jquery-3.11.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>