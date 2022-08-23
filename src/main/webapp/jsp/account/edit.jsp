<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<table class="accountTable signupTable">
		<form action="EditController" name="editForm" method="POST"
			onsubmit="return editCheck();" enctype="multipart/form-data">
			<tr>
				<th id="accountTitle">Edit Profile</th>
			</tr>
			<tr>
				<td align="center">
					<input name="userID" value="${userAccount.id }"
							maxlength="10" autocomplete="off" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="changePW"
						placeholder="Change Password" type="password" maxlength="10" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="changePWC"
						placeholder="Change Password Check" type="password" maxlength="10" autocomplete="off">
						<p>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="editName" value="${userAccount.name }"
						maxlength="10">
				</td>
			</tr>
			<tr>
				<td align="center">
					<div id="accountSubTitle">Birthday</div><p>
					<div id="accountDateSelect">
						<select class="signupSelect" name="year">
							<option>${year }</option>
							<c:forEach var="y" begin="${curYear - 50 }" end="${curYear }">
									<option>${y }</option>
							</c:forEach>
						</select> 년
						<select class="signupSelect" name="month">
							<option>${month }</option>
							<c:forEach var="m" begin="1" end="12">
								<option>${m }</option>
							</c:forEach>
						</select> 월
						<select class="signupSelect" name="day">
							<option>${day }</option>
							<c:forEach var="d" begin="1" end="31">
								<option>${d }</option>
							</c:forEach>
						</select> 일
					</div>
				</td>
			</tr>
			<tr id="addrTr">
				<td align="center">
					<div id="accountSubTitle">Address</div>
					<p>
						<input name="editAddr1" placeholder="우편번호" maxlength="100" value="${addr1}"><p>
						<input name="editAddr2" placeholder="기본주소" maxlength="100" value="${addr2}"><p>
						<input name="editAddr3" placeholder="상세주소" maxlength="100" value="${addr3}">
				</td>
			</tr>
			<tr>
				<td align="center">
					<div id="accountSubTitle">Profile Photo</div>
					<p>
						<input name="editPhoto" maxlength="150" type="file">
						<input name="originalPhoto" value="${userAccount.photo }" type="hidden">
				</td>
			</tr>
			<tr>
				<td align="center">
					<button>Edit</button><p>
		</form>
					<button id="deleteBtn" onclick="goAccountDelete();">Delete Account</button>
				</td>
			</tr>
		</table>
</body>
</html>