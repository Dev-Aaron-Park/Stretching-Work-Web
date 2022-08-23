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
	<table>
		<tr>
			<td class="workingTd" align="center">
				<div>
					<h2>EDIT WORKING</h2>
				</div>
				<form name="addTodoForm" action="WorkingEditController" method="POST" onsubmit="return workingCheck();">
					<input name="no" value="${working.no }" type="hidden" readonly="readonly">
					<input name="id" value="${working.id }" type="hidden" readonly="readonly">
					<div class="addTodoFormDiv">
						<input value="${working.todo }" name="todo"><br>
					</div>
					<div class="addTodoFormDiv">
						<input value="${working.memo }" name="memo"><p>
					</div>
					<div class="addTodoFormDiv">
						Importance<p>
						<select class="importanceSelect" name="imp">
							<option value="2">Middle</option>
							<option value="1">High</option>
							<option value="3">Low</option>
						</select>
					</div>
					<div class="addTodoFormDiv">
						<button>EDIT</button>
				</form>
				<form action="WorkingPageController" method="POST">
						<input name="no" value="${working.no }" type="hidden" readonly="readonly">
						<input name="id" value="${working.id }" type="hidden" readonly="readonly">
						<button>DELETE</button>
				</form>
					</div>
					
			</td>
		</tr>
	</table>
</body>
</html>