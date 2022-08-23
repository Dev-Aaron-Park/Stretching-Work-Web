<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="GuestBookWriteController" method="POST" onsubmit="return " name="guestbookWriteForm">
	<table class="guestbookWriteTable">
		<tr>
			<td class="titleTd" align="center">
				<input name="token" value="${token }" type="hidden">
				<input placeholder="Title" maxlength="32" name="guestbookTitle">
			</td>
		</tr>
		<tr>
			<td class="contentsTd" align="center">
				<textarea placeholder="Contents" maxlength="300" name="guestbookContents"></textarea>
			</td>
		</tr>
		<tr>
			<td class="buttonTd" align="center">
				<button>Write</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>