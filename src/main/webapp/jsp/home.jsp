<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td align="right">
				${result }
			</td>
		</tr>
		<tr>
			<td align="center">
				<br><br><br><br><br><br>
				<img src="img/homeImg.png" height="300px">
			</td>
		</tr>
		<tr>
			<td align="center">
				<c:choose>
					<c:when test="${isLogin }">
						<a id="homeStartBtn" onclick="getUserLocation();"><img src="img/homeBtn.png" height="50px"></a>
					</c:when>
					<c:otherwise>
						<a id="homeStartBtn" href="LoginController"><img src="img/homeBtn.png" height="50px"></a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
</body>
</html>