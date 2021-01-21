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
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<%-- active는 현재 선택이 되었다는 뜻 --%>
						<li class="active"><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%--list 같은 것을 보여줄 때 사용 --%>
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
				<form method="post" action="joinAction.jsp">
					<h3 style="text-align: center;">회원가입 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">
						<%-- placeholder는 어떠한 것도 하지 않았을 때 들어가는 내용 , name은 서버 프로그램 작성 시 사용되기 때문에 대소문자 구분 필요! --%>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
						<%-- placeholder는 어떠한 것도 하지 않았을 때 들어가는 내용 , name은 서버 프로그램 작성 시 사용되기 때문에 대소문자 구분 필요! --%>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20">
						<%-- placeholder는 어떠한 것도 하지 않았을 때 들어가는 내용 , name은 서버 프로그램 작성 시 사용되기 때문에 대소문자 구분 필요! --%>
					</div>
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active">
								<input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="userGender" autocomplete="off" value="여자" checked>여자
							</label>
						</div>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" name="userEmail" maxlength="20">
						<%-- placeholder는 어떠한 것도 하지 않았을 때 들어가는 내용 , name은 서버 프로그램 작성 시 사용되기 때문에 대소문자 구분 필요! --%>
					</div>
					<input type="submit" class="btn btn-primary form-control" value="회원가입">
				</form>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>