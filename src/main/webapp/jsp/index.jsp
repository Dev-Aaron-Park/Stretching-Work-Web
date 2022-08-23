<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Stretching Work</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/account.css">
<link rel="stylesheet" href="css/menu/working.css">
<link rel="stylesheet" href="css/menu/guestbook.css">
<script type="text/javascript" src="js/ValidCheck.js"></script>
<script type="text/javascript" src="js/hwanAccountValidChecker.js"></script>
<script type="text/javascript" src="js/locationCheck.js"></script>
<script type="text/javascript" src="js/link.js"></script>
</head>
<body>
	<table id="siteTitleArea">
		<tr>
			<td align="center">
				<table id="siteTitle">
					<tr>
						<td><a id="titleLogo" href="HomeController"><img src="img/titleLogo.png" height="50px"></a></td>
						<c:choose>
							<c:when test="${isLogin }">
								<td><a id="loginLink" href="LogoutController">Logout</a></td>
								<td><a id="loginLink" href="EditController">Edit</a></td>
								<td>
									<table class="userInfoTable">
										<tr>
											<td>
												<img src="profilePhoto/${userAccount.photo }" height="30px;">
											</td>
											<td>
												${userAccount.id }
											</td>
										</tr>
									</table>
								</td>
							</c:when>
							<c:otherwise>
								<td><a id="loginLink" href="LoginController">Login</a></td>
								<td><a id="loginLink" href="SignupController">SignUp</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td id="siteMenuArea" align="center">
				<table id="siteMenu">
					<tr>
						<td>
							<a href="HomeController">Home</a>
							<a onclick="getUserLocation();">Working</a> 
							<a href="GuestBookController">GuestBook</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<table id="siteContentArea">
		<tr>
			<td align="center"><jsp:include page="${contentsPage }"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>