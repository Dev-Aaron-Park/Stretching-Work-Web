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
	<table class="guestbookTable">
		<tr>
			<td align="right">
				<div id="writeBtn">
					<a href="GuestBookWriteController">
						<img src="img/writeBtn.png" height="40px;">
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center">
				<!-- Memo Card -->
				<c:forEach var="guestbook" items="${gbList }">
					<table class="guestbookCard">
						<tr>
							<td class="guestbookUserIDTd" align="center">
								<div>${guestbook.id }</div>
							</td>
							<td class="guestbookTitleTd" align="center">
								<div>${guestbook.title }</div>
							</td>
							<td class="guestbookTimeTd" align="center">
								<div>${guestbook.date }</div>
							</td>
						</tr>
						<tr>
							<td class="guestbookProfilePhotoTd" align="center">
								<div><img src="profilePhoto/${guestbook.photo }"></div>
							</td>
							<td rowspan="2" colspan="2" class="guestbookContentsTd">
								<div style="margin-left: 5px;">
									${guestbook.contents }
								</div>
							</td>
						</tr>
						<c:if test="${sessionScope.userAccount.id == guestbook.id }">
							<tr>
								<td id="guestbookBtnTd" align="center" colspan="1">
									<form action="GuestBookController" method="POST">
										<input name="guestbookNo" value="${guestbook.no }" type="hidden" readonly="readonly">
										<input name="guestbookTitle" value="${guestbook.title }" type="hidden" readonly="readonly">
										<input name="guestbookContents" value="${guestbook.contents }" type="hidden" readonly="readonly">
										<input name="guestbookId" value="${guestbook.id }" type="hidden" readonly="readonly">
										<button id="guestbookEditBtn">수정</button>
									</form>
									<form action="GuestBookDeleteController" method="POST">
										<input name="guestbookNo" value="${guestbook.no }" type="hidden" readonly="readonly">
										<input name="guestbookId" value="${guestbook.id }" type="hidden" readonly="readonly">
										<!-- 삭제하시겠습니까 ? confirm 처리 -->
										<button onsubmit="return " id="guestbookDeleteBtn">삭제</button>
									</form>
								</td>
							</tr>
						</c:if>
					</table>
				</c:forEach>
				<!-- Memo Card -->
			</td>
		</tr>
	</table>
</body>
</html>