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
	<table id="workingTable">
		<tr>
			<td class="weatherTd">
				<div style="float:left;">
					<img id="weatherImg" src="http://openweathermap.org/img/wn/${weather.icon }@2x.png" height="52px">
				</div>
				<table id="weatherTable">
					<tr>
						<td>
							${weather.city } &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; ${weather.temp } &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; ${weather.humidity }
						</td>
					</tr>
				</table>
			</td>
			<td class="workingTd" rowspan="2" align="center">
				<table class="toDoTable">
					<tr class="toDoTableTitle">
						<th id="timeTh">
							Time						
						</th>
						<th id="todolistTh">
							To Do List
						</th>	
						<th id="memoTh">
							Memo
						</th>
						<th id="importanceTh">
							Importance
						</th>
					</tr>
					<c:forEach var="working" items="${workList }">
						<tr onclick="goWorkingEdit('${working.no }', '${working.id }', '${working.todo }', '${working.memo }');" class="toDoTableTr">
							<td align="center">
								<div>${working.date }</div>
							</td>
							<td align="center">
								<div class="todotext">${working.todo }</div>
							</td>
							<td align="center">
								<div class="memotext">${working.memo }</div>
							</td>
							<td align="center">
								${working.imp }
							</td>
						</tr>
					</c:forEach>
				</table>
				<div id="pageController">
					<c:if test="${page != 1 }">
						<a href="WorkingPageController?page=${page - 1 }">
							<img src="img/pagePrev.png" width="50px">
						</a>
					</c:if>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${page != pageCount }">
						<a href="WorkingPageController?page=${page + 1 }">
							<img src="img/pageNext.png" width="50px">
						</a>
					</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td class="workingTd" align="center">
				<div>
					<img class="addTodoFormDiv" 
							src="img/workingSubTitle.png" 
							width="300px">
				</div>
				<form name="addTodoForm" action="WorkingController" method="POST" onsubmit="return workingCheck();">
					<input name="token" value="${token }" type="hidden">
					<div class="addTodoFormDiv">
						<select class="dateSelect" name="year">
							<c:forEach var="y" begin="${curYear}" end="${curYear + 10 }">
								<option>${y }</option>
							</c:forEach>
						</select> 년
						<select class="dateSelect" name="month">
							<c:forEach var="m" begin="1" end="12">
								<option>${m }</option>
							</c:forEach>
						</select> 월
						<select class="dateSelect" name="day">
							<c:forEach var="d" begin="1" end="31">
								<option>${d }</	option>
							</c:forEach>
						</select> 일<p>
						<select class="dateSelect" name="hour">
							<c:forEach var="h" begin="0" end="23">
								<option>${h }</option>
							</c:forEach>
						</select> 시
						<select class="dateSelect" name="minute">
							<c:forEach var="m" begin="0" end="59">
								<option>${m }</option>
							</c:forEach>
						</select> 분
					</div>
					<div class="addTodoFormDiv">
						<input placeholder="To do" name="todo">
					</div>
					<div class="addTodoFormDiv">
						<input placeholder="To do Memo" name="memo"><p>
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
						<button>ADD</button>
					</div>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>