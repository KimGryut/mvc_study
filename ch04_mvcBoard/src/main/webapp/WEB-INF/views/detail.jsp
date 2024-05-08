<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"></head>
</head>
<body>
	<div class="page-main">
		<h1>게시판 글 상세</h1>
		<ul>
			<li>글번호 : ${num} </li>
			<li>제목 : ${title }</li>
			<li>작성자 : ${name } </li>
		</ul>
		<hr size="1" width="100%" noshade="noshade">
		<p>
			${content }
		</p>
		<div class="align-right">
			작성일 : ${reg_date }
			<input type="button" value="수정"
			  onclick="location.href='updateForm.do?num=${num}'">
		    <input type="button" value="삭제"
			  onclick="location.href='deleteForm.do?num=${num}'">
			<input type="button" value="목록"
			    onclick="location.href='list.do'">  	  
		</div>
	</div>
</body>
</html>