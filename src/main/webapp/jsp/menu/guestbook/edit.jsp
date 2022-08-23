<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="GuestBookEditController" method="POST" onsubmit="return " name="guestbookWriteForm">
	<table class="guestbookWriteTable">
		<tr>
			<td class="titleTd" align="center">
				<input name="guestbookNo" value="${guestbook.no }" type="hidden" readonly="readonly">
				<input value="${guestbook.title }" maxlength="32" name="guestbookEditTitle">
			</td>
		</tr>
		<tr>
			<td class="contentsTd" align="center">
				<textarea maxlength="300" name="guestbookEditContents">${guestbook.contents }</textarea>
			</td>
		</tr>
		<tr>
			<td class="buttonTd" align="center">
				<button>Edit</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>