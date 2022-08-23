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
	<form action="LoginController" name="signupForm" method="POST"
		onsubmit="return signupCheck();" enctype="multipart/form-data">
		<table class="accountTable signupTable">
			<tr>
				<th id="accountTitle">Sign Up</th>
			</tr>
			<tr>
				<td align="center">
					<input name="signupID" placeholder="ID"
							maxlength="10" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="signupPW"
						placeholder="Password" type="password" maxlength="10" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="signupPWC"
						placeholder="Password Check" type="password" maxlength="10">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="signupName" placeholder="Name"
						maxlength="10">
				</td>
			</tr>
			<tr>
				<td align="center">
					<!-- <input name="signupBirthday" placeholder="Birthday      ex) 19800101" maxlength="8"> -->
					<div id="accountSubTitle">Birthday</div><p>
					<div id="accountDateSelect">
						<select class="signupSelect" name="year">
							<c:forEach var="y" begin="${curYear - 50 }" end="${curYear }">
								<option>${y }</option>
							</c:forEach>
						</select> 년
						<select class="signupSelect" name="month">
							<c:forEach var="m" begin="1" end="12">
								<option>${m }</option>
							</c:forEach>
						</select> 월
						<select class="signupSelect" name="day">
							<c:forEach var="d" begin="1" end="31">
								<option>${d }</option>
							</c:forEach>
						</select> 일
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<div id="accountSubTitle">Address</div>
					<p>
						<input name="signupAddr1" placeholder="우편번호" maxlength="100"><p>
						<input name="signupAddr2" placeholder="기본주소" maxlength="100"><p>
						<input name="signupAddr3" placeholder="상세주소" maxlength="100">
				</td>
			</tr>
			<tr>
				<td align="center">
					<div id="accountSubTitle">Profile Photo</div>
					<p>
						<input name="signupPhoto" maxlength="150" type="file">
				</td>
			</tr>
			<tr>
				<td align="center">
					<button>Sign Up</button>
				</td>
			</tr>
			<tr>
				<td align="center">
					<a id="signupLoginbtn" href="LoginController">Login</a><p>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>