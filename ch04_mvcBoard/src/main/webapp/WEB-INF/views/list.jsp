<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myStyle.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<h2>게시판 목록</h2>
		<div class="align-right">
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'" id="btn">
		</div>
		<c:if test="${count ==0}">
			<div class="result-display">표시할 게시물이 없습니다.</div>
		</c:if>
		<c:if test="${count!=0}">
			<!-- 목록 출력 시작 -->
			<table>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.num}</td>
						<td><a href="detail.do?num=${board.num}">${board.title}</a></td>
						<td>${board.name}</td>
						<td>${board.reg_date}</td>
					</tr>
				</c:forEach>
			</table> 
			<!-- 목록 출력 끝 -->
			<!-- 페이지 표시 시작 -->
			<div class="align-center">
				${page}
			</div>
			<!-- 페이지 표시 끝 -->
		</c:if>
	</div>
	
</body>
</html>	