<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<form name="loginForm" action="HomeController" method="POST" onsubmit="return loginCheck();">
	<table class="accountTable loginTable">
		<tr>
			<th id="accountTitle">
				Login
			</th>
		</tr>
		<tr>
			<td align="center">
				<input name="loginID" placeholder="ID">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input name="loginPW" type="password" placeholder="Password">
			</td>
		</tr>
		<tr>
			<td align="center">
				<button>Login</button>
			</td>
		</tr>
		<tr>
			<td align="center">
				<a href="SignupController">Sign Up</a>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>